package com.example.demo.app.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

	@GetMapping("/form")
	public String form(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form";
	}

//	値をPostした時に、失敗したらFormに戻す、成功したらconfirmページにいく
	@PostMapping("/confirm")
//	@ValidatedでバリデーションがInquiryFormにかかる
	public String confirm(@Validated InquiryForm inquiryForm,
//			バリデーション後の結果
			BindingResult result,
			Model model) {
//		バリデーションに引っかかった場合の処理
		if(result.hasErrors()) {
			model.addAttribute("title", "Inquiry Form");
			return "inquiry/form";
		}
		model.addAttribute("title", "Confirm Page");
		return "inquiry/confirm";
	}

}
