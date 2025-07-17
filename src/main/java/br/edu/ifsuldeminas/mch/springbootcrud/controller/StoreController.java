package br.edu.ifsuldeminas.mch.springbootcrud.controller;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Store;
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
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public String listStores(Model model) {
        List<Store> stores = storeRepository.findAll();
        model.addAttribute("stores", stores);
        return "store/stores";
    }

    @GetMapping("/stores/form")
    public String showStoreForm(@ModelAttribute("store") Store store) {
        return "store/store_form";
    }

    @PostMapping("/stores/new")
    public String saveStore(@Valid @ModelAttribute("store") Store store, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "store/store_form";
        }
        storeRepository.save(store);
        return "redirect:/stores";
    }

    @GetMapping("/stores/update/{id}")
    public String updateStore(@PathVariable("id") Integer id, Model model) {
        Optional<Store> optStore = storeRepository.findById(id);
        if (optStore.isEmpty()) {
            throw new IllegalArgumentException("Loja inválida.");
        }
        model.addAttribute("store", optStore.get());
        return "store/store_form";
    }

    @GetMapping("/stores/delete/{id}")
    public String deleteStore(@PathVariable("id") Integer id) {
        Optional<Store> optStore = storeRepository.findById(id);
        if (optStore.isEmpty()) {
            throw new IllegalArgumentException("Loja inválida.");
        }
        storeRepository.delete(optStore.get());
        return "redirect:/stores";
    }
}