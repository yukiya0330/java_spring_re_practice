package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.InquiryDao;
import com.example.demo.entity.Inquiry;

//DIコンテナでシングルトンとしてインスタンス化される
@Service
public class InquiryServiceImpl implements InquiryService {

//	daoクラスの初期化
	private final InquiryDao dao;

//	デフォルトコンストラクタ
	@Autowired InquiryServiceImpl(InquiryDao dao){
		this.dao = dao;
	}


	@Override
	public void save(Inquiry inquiry) {
		dao.insertInquiry(inquiry);
	}

	@Override
	public List<Inquiry> getAll() {
		return dao.getAll();
	}

}
