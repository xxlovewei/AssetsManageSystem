package com.dt.core.shiro;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;

public class RoleAllowsAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {  
	  
    
    
    public RoleAllowsAnnotationMethodInterceptor() {  
        super(new RolesAllowedAnnotationHandler());  
    }  
  
    public RoleAllowsAnnotationMethodInterceptor(AnnotationResolver resolver) {  
        super(new RolesAllowedAnnotationHandler(),resolver);  
    }  
      
    
    @Override  
    public void assertAuthorized(MethodInvocation mi) throws AuthorizationException {  
    	
    	System.out.println("DFFDDDDFDF"+mi);
    	super.assertAuthorized(mi);
    	
//        try {  
//            AuthorizingAnnotationHandler handler = (AuthorizingAnnotationHandler) getHandler();  
//            if(handler instanceof RolesAllowedAnnotationHandler) {  
//                ((RolesAllowedAnnotationHandler) handler).assertAuthorized(mi);  
//            } else {  
//                handler.assertAuthorized(getAnnotation(mi));  
//            } 
//        }  
//        catch(AuthorizationException ae) {  
//            // Annotation handler doesn't know why it was called, so add the information here if possible.  
//            // Don't wrap the exception here since we don't want to mask the specific exception, such as  
//            // UnauthenticatedException etc.  
//            if (ae.getCause() == null) ae.initCause(new AuthorizationException("Not authorized to invoke method: " + mi.getMethod()));  
//            throw ae;  
//        }  
    } 
      
  
}  