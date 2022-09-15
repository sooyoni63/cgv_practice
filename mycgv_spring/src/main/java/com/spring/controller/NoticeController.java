package com.spring.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvNoticeVO;
import com.spring.service.NoticeServiceImpl;
import com.spring.service.PageServiceImpl;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeServiceImpl noticeService;
	
	@Autowired
	private PageServiceImpl pageService;
	
	/**
	 * notice_content_json.do : �������� �������� Ajax�� ȣ��
	 */
	@ResponseBody
	@RequestMapping(value="/notice_content_json.do", method=RequestMethod.GET
	 					, produces="text/plain;charset=UTF-8")
	public String notice_content_json(String nid) {
		//String nid=request.getParameter("nid");
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = noticeService.getContent(nid);
		
		Gson gson = new Gson();
		if(vo != null){
			noticeService.getUpdateHits(nid);
		}
		JsonObject jobject = new JsonObject();
		jobject.addProperty("nid", vo.getNid());
		jobject.addProperty("ntitle", vo.getNtitle());
		jobject.addProperty("ncontent", vo.getNcontent());
		jobject.addProperty("nhits", vo.getNhits());
		jobject.addProperty("ndate", vo.getNdate());
		
		//{"nid":n_10,"ntitle":"���������׽�Ʈ" ...}
		

		return gson.toJson(jobject);
		
	}
	
	/**
	 * notice_list_json.do : �������� ��ü ����Ʈ�� Ajax�� ȣ��
	 */
	@ResponseBody
	@RequestMapping(value="/notice_list_json.do", method=RequestMethod.GET
	 					, produces="text/plain;charset=UTF-8")
	public String notice_list_json(String rpage) {
		
		Map<String, Integer> param = pageService.getPageResult(rpage, "notice", noticeService);
		ArrayList<CgvNoticeVO> list = noticeService.getList(param.get("startCount"), param.get("endCount"));
		
		//{list:[{rno:1,ntitle:"ž��~",ndate:"2022-08-01",nhits:100},
		//         {�ι�° vo ��},{�¹�° vo ��}...]}
			
		//gson ���̺귯���� �̿��Ͽ� �ڹ� list ��ü�� JSON ��ü�� ��ȯ
		JsonObject jobject = new JsonObject(); //CgvNoticeVO
		JsonArray jarray = new JsonArray();  //ArrayList
		Gson gson = new Gson();
		
		for(CgvNoticeVO vo : list){
			JsonObject jo = new JsonObject();
			jo.addProperty("rno", vo.getRno());
			jo.addProperty("nid", vo.getNid());
			jo.addProperty("ntitle", vo.getNtitle());
			jo.addProperty("ndate", vo.getNdate());
			jo.addProperty("nhits", vo.getNhits());
			
			jarray.add(jo);
		}// [{rno:1,ntitle:��վ��,ndate:2022-08-01,nhits:100},... ]
		
		jobject.add("list", jarray); 
		jobject.addProperty("dbCount", param.get("dbCount"));
		jobject.addProperty("pageSize", param.get("pageSize"));
		jobject.addProperty("rpage", param.get("rpage"));
		jobject.addProperty("pageCount", param.get("pageCount"));
		//{list:[{rno:1,ntitle:��վ��,ndate:2022-08-01,nhits:100},. ],
		// dbCount:10, rpage:1, pageSize:5 	
		// }
		
		//out.write(gson.toJson(jobject));
		
		
		
		return gson.toJson(jobject);
	}
	
		
	/**
	 * notice_list.do : �������� ��ü ����Ʈ 
	 */
	@RequestMapping(value="/notice_list.do", method=RequestMethod.GET)
	public String notice_list() {
		return "/notice/notice_list";
	}
}
