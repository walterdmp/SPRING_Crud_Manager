package br.edu.ifsuldeminas.mch.springbootcrud.controller;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Boot;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.BootRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class BootController {

    @Autowired
    private BootRepository bootRepository;

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/boots")
    public String listBoots(Model model) {
        List<Boot> boots = bootRepository.findAll();
        model.addAttribute("boots", boots);
        return "boot/boots";
    }

    @GetMapping("/boots/form")
    public String showBootForm(@ModelAttribute("boot") Boot boot, Model model) {
        model.addAttribute("stores", storeRepository.findAll());
        return "boot/boot_form";
    }

    @PostMapping("/boots/new")
    public String saveBoot(@Valid @ModelAttribute("boot") Boot boot, BindingResult validationResult, Model model) {
        if (validationResult.hasErrors()) {
            model.addAttribute("stores", storeRepository.findAll());
            return "boot/boot_form";
        }
        bootRepository.save(boot);
        return "redirect:/boots";
    }

    @GetMapping("/boots/update/{id}")
    public String updateBoot(@PathVariable("id") Integer id, Model model) {
        Optional<Boot> optBoot = bootRepository.findById(id);
        if (optBoot.isEmpty()) {
            throw new IllegalArgumentException("Botina inválida.");
        }
        model.addAttribute("boot", optBoot.get());
        model.addAttribute("stores", storeRepository.findAll());
        return "boot/boot_form";
    }

    @GetMapping("/boots/delete/{id}")
    public String deleteBoot(@PathVariable("id") Integer id) {
        Optional<Boot> optBoot = bootRepository.findById(id);
        if (optBoot.isEmpty()) {
            throw new IllegalArgumentException("Botina inválida.");
        }
        bootRepository.delete(optBoot.get());
        return "redirect:/boots";
    }
}