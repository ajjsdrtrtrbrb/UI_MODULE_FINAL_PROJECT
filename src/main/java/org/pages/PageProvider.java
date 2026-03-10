package org.pages;

import org.openqa.selenium.WebDriver;
import org.pages.BackOffice.BackOfficeLoginPage;
import org.pages.BackOffice.BonusesPage;
import org.pages.BackOffice.ClientsPage;
import org.pages.BackOffice.LeftMenuPage;
import org.pages.BackOffice.boForms.CreateBonusForm;
import org.pages.forms.RegistrationForm;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public AuthPage getAuthPage() {
        return new AuthPage(webDriver);
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }

    public RegistrationForm getRegistrationForm() {
        return new RegistrationForm(webDriver);
    }

    public BackOfficeLoginPage getBackOfficeLoginPage() {
        return new BackOfficeLoginPage(webDriver);
    }

    public ClientsPage getClientsPage() {
        return new ClientsPage(webDriver);
    }

    public BonusesPage getBonusesPage() {
        return new BonusesPage(webDriver);
    }

    public CreateBonusForm getCreateBonusForm() {
        return new CreateBonusForm(webDriver);
    }

    public LeftMenuPage getLeftMenuPage(){return new LeftMenuPage(webDriver);}

    public BonusesSection getBonusesSection() {return new BonusesSection(webDriver);}
}
