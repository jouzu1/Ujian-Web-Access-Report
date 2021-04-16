package com.ujian.controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ujian.utility.FileUtility;

import com.ujian.entity.Korban;
import com.ujian.service.ModelKorban;

@Controller
public class KorbanPage {
	@Autowired
	ModelKorban modelKorban;
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		

		return "login";
		
	}

	@GetMapping("/korban/view")
	public String home(Model model) {
		model.addAttribute("totalLaporan", modelKorban.getKorban().size());
		model.addAttribute("listLaporan", modelKorban.getKorban());
		model.addAttribute("ditanggapi", modelKorban.getKorban().size());
		model.addAttribute("proses", modelKorban.getKorban().size());
		model.addAttribute("active", 1);
		return "dashboard";
	}
	
	@GetMapping("/korban/Adminview")
	public String homeView(Model model) {
		model.addAttribute("listLaporan", modelKorban.getKorban());
		
		model.addAttribute("active", 3);
		return "view_korban";
	}

	@GetMapping("/korban/add")
	public String viewAddLaporn(Model model) {
		model.addAttribute("korban", new Korban());
		model.addAttribute("active", 2);
		return "tambah_laporan";
	}

	@PostMapping("/korban/vieew")
	public String addKorban(@RequestParam(value = "file") MultipartFile file, @ModelAttribute Korban korban,
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
			return "redirect:/korban/view";
		}
	}
	
	
	
	@GetMapping("/korban/approve/{id}")
    public String approveLaporan(@PathVariable String id, Model model) {

        Korban updateStatus = modelKorban.getKorbanById(id);
        updateStatus.setStatus("Approve");
        modelKorban.save(updateStatus);

        return "redirect:/korban/Adminview";
    }
	@GetMapping("/korban/reject/{id}")
    public String rejectLaporan(@PathVariable String id, Model model) {

        Korban updateStatus = modelKorban.getKorbanById(id);
        updateStatus.setStatus("Reject");
        modelKorban.save(updateStatus);

        return "redirect:/korban/Adminview";
    }
//	@PostMapping("/korban/sendApprove")
//	  public String addApprove(@ModelAttribute Korban korban, Model model) {
//		
//		// buat penampung data mahasiswa di halaman htmlnya
//		korban.setStatus("Approve");	
//		korban.setNama(korban.getNama());
//		this.modelKorban.addKorban(korban);
////		model.addAttribute("listLaporan", modelKorban.getKorban());
//		
//		
//		return "redirect:/korban/Adminview";
//	}
//	
//	@PostMapping("/korban/sendReject")
//	  public String addReject(@ModelAttribute Korban korban, Model model) {
//		
//		// buat penampung data mahasiswa di halaman htmlnya
//		korban.setStatus("Reject");		
//		this.modelKorban.addKorban(korban);
//		model.addAttribute("listLaporan", modelKorban.getKorban());
//		
//		
//		return "redirect:/korban/Adminview";
//	}
	
//	@GetMapping("/korban/update/{id}")
//	public String viewUpdateMahasiswa(@PathVariable String id, Model model) {
//		
//		Korban mahasiswa = modelKorban.getKorbanById(id);
//		// buat penampung data mahasiswa di halaman htmlnya
//		model.addAttribute("mahasiswa",mahasiswa);
//		
//		return "redirect:/korban/Adminview";
//	}
}
