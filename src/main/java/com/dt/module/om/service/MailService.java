package com.dt.module.om.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ToolUtil;

import jodd.mail.Email;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;

/**
 * @author: jinjie
 * @date: 2018年4月27日 上午9:42:01
 * @Description: TODO
 */
@Service
@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
public class MailService extends BaseService {
	private static Logger _log = LoggerFactory.getLogger(MailService.class);

	@Value("${mail.from}")
	private String mailfrom;

	@Value("${mail.user}")
	private String mailuser;

	@Value("${mail.pwd}")
	private String mailpwd;

	@Value("${mail.smtp}")
	private String mailsmtp;

	@Value("${mail.port}")
	private String mailport;

	public R sendMail(String body, String title) {
		if (ToolUtil.isOneEmpty(mailfrom, mailuser, mailpwd, mailsmtp, mailport)) {
			_log.info("邮件无法发送，请检测配置");
			return R.FAILURE("邮件无法发送，请检测配置");
		}
		String sql = "select b.* from mn_mail_user a,sys_user_info b where a.user_id=b.user_id and b.deleted='N' "
				+ "and b.mail is not null";
		RcdSet rs = db.query(sql);
		for (int i = 0; i < rs.size(); i++) {
			String mail = rs.getRcd(i).getString("mail");
			if (mail.indexOf("@") == -1) {
				continue;
			}
			_log.info("send mail to:" + mail);
			Email email = Email.create();
			try {
				email.from(mailfrom).to(mail);
				email.subject(title);
				email.addHtml("<html><meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">" + "<body>"
						+ body + "</body></html>");
				@SuppressWarnings("rawtypes")
				SmtpServer smtpServer = SmtpServer.create(mailsmtp, ToolUtil.toInt(mailport, 25))
						.authenticateWith(mailuser, mailpwd).timeout(100000);
				SendMailSession session = smtpServer.createSession();
				session.open();
				session.sendMail(email);
				session.close();
			} catch (Exception e) {
			}
		}

		// 发邮件
		_log.info(title + ".邮件无法成功");
		return R.SUCCESS_OPER();
	}
}
