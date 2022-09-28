package com.example.demo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inquiry;

//データベースを操作するクラスだということを宣言
@Repository
public class InquiryDaoImpl implements InquiryDao {

//	JdbcTemplateの初期化
	private final JdbcTemplate jdbcTemplate;

//	データベースの操作が行えるようになる
	@Autowired
	public InquiryDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertInquiry(Inquiry inquiry) {
//		SQLインジェクションを防ぐコード
		jdbcTemplate.update("INSERT INTO inquiry(name, email, contents, created) VALUES(?, ?, ?, ?)",
				inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getCreated());

	}
	
//	データベースから値を取り出すメソッド
	@Override
	public List<Inquiry> getAll() {
		String sql = "SELECT id, name, emal, contents, created FROM inquiry";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		List<Inquiry> list = new ArrayList<Inquiry>();
		for(Map<String, Object> result : resultList) {
			Inquiry inquiry = new Inquiry();
			inquiry.setId((int)result.get("id"));
			inquiry.setName((String)result.get("name"));
			inquiry.setEmail((String)result.get("email"));
			inquiry.setContents((String)result.get("contents"));
			inquiry.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			list.add(inquiry);
		}
		return list;
	}

}
