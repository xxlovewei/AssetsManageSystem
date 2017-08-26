package com.dt.module.base.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.db.DB;

@Controller()
@RequestMapping("/file")
public class FileUpDownController extends BaseController {
	@Autowired
	private DB db = null;

	private static Logger _log = LoggerFactory.getLogger(FileUpDownController.class);
	
	@RequestMapping("/fileupload.do")
	@Res
	@Acl(value = "allow")
	public ResData fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String uuid = request.getParameter("uuid");
		if (type == null || type.equals("")) {
			return ResData.FAILURE("请输入文件类型");
		}
		if (uuid == null || uuid.equals("")) {
			return ResData.FAILURE("请输入uuid");
		}
		String bus = request.getParameter("bus");
		if (bus == null || bus.equals("")) {
			return ResData.FAILURE("请输入业务号");
		}
		Rcd fileinfo = db.uniqueRecord("select * from SYS_FILE_CONF where id=? and is_used='Y'", bus);
		if (fileinfo == null) {
			return ResData.FAILURE("数据库并未配置");
		}
		// 从数据库中获取
		String bus_path = fileinfo.getString("path");
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		_log.info("Type:" + type);
		if (type.equals("image")) {
			String name = "Image_" + uuid;
			// 获得第1张图片（根据前台的name名称得到上传的文件）
			MultipartFile image = multipartRequest.getFile("file[0]");
			if (image == null) {
				return ResData.FAILURE("上传的文件不存在");
			}
			// dir:/tmp1/wtpwebapps/tt/..
			String dir = getWebRootDir() + ".." + File.separatorChar;
			_log.info("Upload Dir:" + dir);
			Calendar cale = Calendar.getInstance();
			String path = "upload" + File.separatorChar + bus_path + File.separatorChar + cale.get(Calendar.YEAR) + ""
					+ File.separatorChar + "" + cale.get(Calendar.MONTH) + "" + File.separatorChar + ""
					+ cale.get(Calendar.DAY_OF_MONTH) + "" + File.separatorChar;
			_log.info("Upload Path:" + path);
			File f = new File(dir + path + name + ".png");
			ResData vaf = valid(f);
			if (!vaf.isSuccess()) {
				return vaf;
			}
			_log.info("File:" + f.getAbsolutePath());
			_log.info("FileParent:" + f.getParentFile().getAbsolutePath());
			image.transferTo(f);
			Insert ins = new Insert("sys_files");
			ins.set("id", uuid);
			ins.set("path", path + f.getName());
			ins.set("type", type);
			ins.set("bus", bus);
			db.execute(ins);
		}
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/imagedown.do")
	@Acl(value = "allow")
	public void imagedown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			id = "none";
		}
		// id = "F607ABCD1F4583F41EE166A501572FF6";
		String sql = "select * from sys_files where id=?";
		Rcd set = db.uniqueRecord(sql, id);
		File file = null;
		String ct = "";
		try {
			String type = set.getString("type");
			if (type.equals("image")) {
				ct = "image/jpeg";
			}
			String fileurl = set.getString("path");
			String filePath = getWebRootDir() + ".." + File.separatorChar + fileurl;
			_log.info("filePath" + filePath);
			String heightStr = request.getParameter("height");
			if (heightStr == null || heightStr.isEmpty())
				heightStr = request.getParameter("h");
			String widthStr = request.getParameter("width");
			if (widthStr == null || widthStr.isEmpty())
				widthStr = request.getParameter("w");
			String crop = request.getParameter("crop");
			String fit = request.getParameter("fit");
			fit = "width";
			crop = "TL";
			int width = 0;
			int height = 0;
			try {
				width = Integer.parseInt(widthStr);
			} catch (Exception e) {
			}
			;
			try {
				height = Integer.parseInt(heightStr);
			} catch (Exception e) {
			}
			file = new File(filePath);
			_log.info("图片位置:" + file.getAbsolutePath());
			if (!file.exists()) {
				_log.info("图片不存在,去获取默认图片");
				file = getDefaultImageFile();
			} else {
				_log.info("图片存在,开始裁截");
				int ow = 0;
				int oh = 0;
				_log.info("height" + height);
				_log.info("width" + width);
				file = scale5(id, filePath, height, width, ow, oh, fit, crop);
				if (file == null || !file.exists()) {
					_log.info("裁剪失败,去获取默认图片");
					file = getDefaultImageFile();
				}
			}
		} catch (Exception e) {
			file = getDefaultImageFile();
		}
		BufferedImage input = ImageIO.read(file);
		response.reset();
		response.setContentType(ct);
		ImageIO.write(input, "png", response.getOutputStream());
	}
	public File getDefaultImageFile() {
		_log.info("获取默认图片:" + getWebRootDir() + File.separatorChar + "image" + File.separatorChar + "blank.jpg");
		return new File(getWebRootDir() + File.separatorChar + "image" + File.separatorChar + "blank.jpg");
	}

	public static final String THUMBS = "thumbs";

	public final static File scale5(String id, String srcImageFile, int height, int width, int origWidth, int origHeigh,
			String fitType, String crop) throws IOException {
		if (height <= 0 && width <= 0)
			return new File(srcImageFile);
		File srcFile = new File(srcImageFile);
		String format = null;
		int i = srcFile.getName().lastIndexOf(".");
		if (i > 0) {
			format = srcFile.getName().substring(i + 1);
		}
		if (format == null)
			format = "jpg";
		BufferedImage srcImage = null;
		SmartImageScalr sis = null;
		if (origWidth > 0 && origHeigh > 0) {
			sis = new SmartImageScalr(width, height, fitType, crop, origWidth, origHeigh);
		} else {
			srcImage = ImageIO.read(srcFile);
			sis = new SmartImageScalr(width, height, fitType, crop, srcImage);
		}
		String thumbFilePath = sis.getFileName(srcFile, format);
		thumbFilePath = srcFile.getParentFile().getAbsolutePath() + File.separatorChar + THUMBS + File.separatorChar
				+ thumbFilePath;
		File thumbFile = new File(thumbFilePath);
		if (thumbFile.exists())
			return thumbFile;
		// if (origWidth <= 0 || origHeigh <= 0) {
		//
		// JSONObject info = new JSONObject();
		// info.put("width", srcImage.getWidth());
		// info.put("height", srcImage.getHeight());
		//
		// // DB.instance().execute("update sys_file set infos=? where
		// // id=?",info.toJSONString(),id);
		// }
		if (srcImage == null)
			srcImage = ImageIO.read(srcFile);
		BufferedImage thumbImage = sis.scaleAndCrop(srcImage);
		if (!thumbFile.getParentFile().exists())
			thumbFile.getParentFile().mkdirs();
		ImageIO.write(thumbImage, format, thumbFile);
		return thumbFile;
	}
	private static ResData valid(File file) {
		File parentPath = file.getParentFile();
		if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
			return ResData.FAILURE("创建文件失败");
		}
		if (!parentPath.canWrite()) {
			return ResData.FAILURE("不可写");
		}
		return ResData.SUCCESS_OPER();
	}
	private static String getWebRootDir() {
	 
		return ToolUtil.getRealPathInWebApp("");
	}
}
