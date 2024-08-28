package com.mutana.CarSales.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping
    @PreAuthorize("hasAnyRole('VIEWER', 'EDITOR', 'ADMIN')")
    public String getAllSales(Model model) {
        model.addAttribute("sales", salesService.getAllSales());
        return "sales/list";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('VIEWER', 'EDITOR', 'ADMIN')")
    public String getSale(@PathVariable Long id, Model model) {
        model.addAttribute("sale", salesService.getSaleById(id));
        return "sales/detail";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAnyRole('EDITOR', 'ADMIN')")
    public String newSaleForm(Model model) {
        model.addAttribute("sale", new SalesModel());
        return "sales/form";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('EDITOR', 'ADMIN')")
    public String createSale(@ModelAttribute SalesModel sale) {
        salesService.saveSale(sale);
        return "redirect:/sales";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAnyRole('EDITOR', 'ADMIN')")
    public String editSaleForm(@PathVariable Long id, Model model) {
        model.addAttribute("sale", salesService.getSaleById(id));
        return "sales/form";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('EDITOR', 'ADMIN')")
    public String updateSale(@PathVariable Long id, @ModelAttribute SalesModel sale) {
        sale.setSaleId(id);
        salesService.saveSale(sale);
        return "redirect:/sales";
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSale(@PathVariable Long id) {
        salesService.deleteSale(id);
        return "redirect:/sales";
    }
}
