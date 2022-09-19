package com.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		    throws Exception {
			
		//������ üũ�ϴ� ����
		//1. request ��ü�� ���� session ���� ��������
		HttpSession session = request.getSession();
		
		//2. �α��� ���� �� session�� �α��� ����Ű(sid) ��� Ŭ���̾�Ʈ���� ����
		String sid = (String)session.getAttribute("sid"); //String�� �ƴ� Object�� convert�ؾ��Ѵٴ� ���� Ȯ�� -> ����ȯ ���� //�α��� ������ ����: �ڽ��� ID, �α������� ���� �����: null������ ����
		if(sid == null) {
			//�α������� ���� ����� : null
			response.sendRedirect("http://localhost:9000/mycgv/login.do");
			return false;
		}else {
			//sid�� null�� �ƴϰ�, admin�� ��쿡�� ����
			if(!sid.equals("admin")) {
				response.sendRedirect("http://localhost:9000/mycgv/login.do");
				return false;
			}
		}
		
			return true;
		}
	
}
