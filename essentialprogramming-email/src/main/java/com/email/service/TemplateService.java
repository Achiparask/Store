package com.email.service;

import com.email.model.Template;

import java.util.Locale;
import java.util.Map;

public interface TemplateService {

    String generateHTML(Map<String, Object> content, Template template);

    String generateHTML(Map<String, Object> content, Template template, Locale locale);

    byte[] generatePDF(Map<String, Object> content, Template template, Locale locale);
}
