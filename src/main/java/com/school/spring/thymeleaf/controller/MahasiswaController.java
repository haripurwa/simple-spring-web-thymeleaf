package com.school.spring.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import com.school.spring.thymeleaf.model.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.school.spring.thymeleaf.repository.MahasiswaRepository;

@Controller
public class MahasiswaController {

  @Autowired
  private MahasiswaRepository mahasiswaRepository;

  @GetMapping("/mahasiswa")
  public String getAll(Model model, @Param("keyword") String keyword) {
    try {
      List<Mahasiswa> mahasiswa = new ArrayList<Mahasiswa>();

      if (keyword == null) {
        mahasiswaRepository.findAll().forEach(mahasiswa::add);
      } else {
        mahasiswaRepository.findByNameContainingIgnoreCase(keyword).forEach(mahasiswa::add);
        model.addAttribute("keyword", keyword);
      }

      model.addAttribute("mahasiswa", mahasiswa);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }

    return "mahasiswa";
  }

  @GetMapping("/mahasiswa/new")
  public String addMahasiswa(Model model) {
    Mahasiswa mahasiswa = new Mahasiswa();
    mahasiswa.setPublished(true);

    model.addAttribute("mahasiswa", mahasiswa);
    model.addAttribute("pageTitle", "Create new Mahasiswa");

    return "mahasiswa_form";
  }

  @PostMapping("/mahasiswa/save")
  public String saveMahasiswa(Mahasiswa mahasiswa, RedirectAttributes redirectAttributes) {
    try {
      mahasiswaRepository.save(mahasiswa);

      redirectAttributes.addFlashAttribute("message", "The mahasiswa has been saved successfully!");
    } catch (Exception e) {
      redirectAttributes.addAttribute("message", e.getMessage());
    }

    return "redirect:/mahasiswa";
  }

  @GetMapping("/mahasiswa/{id}")
  public String editMahasiswa(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      Mahasiswa mahasiswa = mahasiswaRepository.findById(id).get();

      model.addAttribute("mahasiswa", mahasiswa);
      model.addAttribute("pageTitle", "Edit Mahasiswa (ID: " + id + ")");

      return "mahasiswa_form";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());

      return "redirect:/mahasiswa";
    }
  }

  @GetMapping("/mahasiswa/delete/{id}")
  public String deleteMahasiswa(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      mahasiswaRepository.deleteById(id);

      redirectAttributes.addFlashAttribute("message", "The mahasiswa with id=" + id + " has been deleted successfully!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());
    }

    return "redirect:/mahasiswa";
  }

  @GetMapping("/mahasiswa/{id}/published/{status}")
  public String mahasiswaPublishedStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean published,
      Model model, RedirectAttributes redirectAttributes) {
    try {
      mahasiswaRepository.updatePublishedStatus(id, published);

      String status = published ? "published" : "disabled";
      String message = "The Mahasiswa id=" + id + " has been " + status;

      redirectAttributes.addFlashAttribute("message", message);
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());
    }

    return "redirect:/mahasiswa";
  }
}
