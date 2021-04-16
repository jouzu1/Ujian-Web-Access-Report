package com.ujian.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ujian.utility.FileUtility;
import com.ujian.entity.Korban;
import com.ujian.service.ModelKorban;

@Controller
public class KorbanPage {
	@Autowired
	ModelKorban modelKorban;

	@GetMapping("/korban/view")
	public String home(Model model) {
		model.addAttribute("totalLaporan", modelKorban.getKorban().size());
		model.addAttribute("ditanggapi", modelKorban.getKorban().size());
		model.addAttribute("proses", modelKorban.getKorban().size());
		model.addAttribute("active", 1);
		return "dashboard";
	}

	@GetMapping("/korban/add")
	public String viewAddLaporn(Model model) {
		model.addAttribute("korban", new Korban());
		return "tambah_laporan";
	}

	@PostMapping("/korban/vieew")
	public String addPertanyaan(@RequestParam(value = "file") MultipartFile file, @ModelAttribute Korban korban,
			Model model) throws IOException {
		{
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

			String uploadDir = "user-photos/";

			FileUtility.saveFile(uploadDir, fileName, file);

			korban.setGambar("/" + uploadDir + fileName);
			this.modelKorban.addKorban(korban);
			model.addAttribute("totalLaporan", modelKorban.getKorban().size());
			model.addAttribute("ditanggapi", modelKorban.getKorban().size());
			model.addAttribute("proses", modelKorban.getKorban().size());
			model.addAttribute("active", 2);

			return "redirect:/korban/view";
		}
	}
}
