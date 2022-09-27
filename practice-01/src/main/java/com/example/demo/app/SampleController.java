package com.example.demo.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleController {

//	jdbcTemplate初期化
	private final JdbcTemplate jdbcTemplate;

//	JdbcTemplateを使えるようにする
	@Autowired
	public SampleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@GetMapping("/test")
	public String test(Model model) {

//		データベースへの操作の命令
		String sql = "SELECT id, name, email "
				+ "FROM inquiry WHERE id = 1";
//		Mapからsql取得
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);

        model.addAttribute("title", "Inquiry Form");
//      htmlに送るデータの取得
        model.addAttribute("name", map.get("name"));
        model.addAttribute("email", map.get("email"));
		return "test";
	}

}