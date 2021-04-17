package com.ujian.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ujian.entity.Korban;
import com.ujian.service.ModelKorban;
import com.ujian.utility.FileUtility;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
		model.addAttribute("fieldDisabled", "Selesai");
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
	public String addKorban(@RequestParam(value = "file") MultipartFile file, @ModelAttribute Korban korban
			,Model model) throws IOException {
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
	
	@GetMapping("/korban/report/pdf")
	public String exportPDF() {
		try {
		File file = ResourceUtils.getFile("classpath:Report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		List<Korban> lstKorban = modelKorban.getKorban();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstKorban);
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy","Jouzu");
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String path = "D:\\Report.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint,path);
        
       
		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}	
		return "redirect:/korban/Adminview";
	}
}