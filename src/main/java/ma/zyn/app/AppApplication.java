package ma.zyn.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;


import ma.zyn.app.zynerator.security.bean.*;
import ma.zyn.app.zynerator.security.common.AuthoritiesConstants;
import ma.zyn.app.zynerator.security.service.facade.*;

import ma.zyn.app.bean.core.exigence.FamilleExigence;
import ma.zyn.app.service.facade.admin.exigence.FamilleExigenceAdminService;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.service.facade.admin.projet.DossierProjetExigenceEtatAdminService;

import ma.zyn.app.zynerator.security.bean.User;
import ma.zyn.app.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class AppApplication {
    public static ConfigurableApplicationContext ctx;


    //state: primary success info secondary warning danger contrast
    //_STATE(Pending=warning,Rejeted=danger,Validated=success)
    public static void main(String[] args) {
        ctx=SpringApplication.run(AppApplication.class, args);
    }


    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService ) {
    return (args) -> {
        if(true){

            createFamilleExigence();
            createDossierProjetExigenceEtat();

        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));
        

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
        userForAdmin.setId(1L);
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
        roleForAdmin.setId(1L);
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

            }
        };
    }



    private void createFamilleExigence(){
            FamilleExigence itemSuccess = new FamilleExigence();
            itemSuccess.setId(1L);
            itemSuccess.setStyle("success");
            itemSuccess.setLibelle("Validated");
            itemSuccess.setCode("Validated");
            familleExigenceService.create(itemSuccess);
            FamilleExigence itemDanger = new FamilleExigence();
            itemDanger.setId(2L);
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Blocked");
            itemDanger.setCode("Blocked");
            familleExigenceService.create(itemDanger);
            FamilleExigence itemWarning = new FamilleExigence();
            itemWarning.setId(3L);
            itemWarning.setStyle("warning");
            itemWarning.setLibelle("Pending");
            itemWarning.setCode("Pending");
            familleExigenceService.create(itemWarning);

    }
    private void createDossierProjetExigenceEtat(){
            DossierProjetExigenceEtat itemSuccess = new DossierProjetExigenceEtat();
            itemSuccess.setId(1L);
            itemSuccess.setStyle("success");
            itemSuccess.setLibelle("Validated");
            itemSuccess.setCode("Validated");
            dossierProjetExigenceEtatService.create(itemSuccess);
            DossierProjetExigenceEtat itemDanger = new DossierProjetExigenceEtat();
            itemDanger.setId(2L);
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Rejected");
            itemDanger.setCode("Rejected");
            dossierProjetExigenceEtatService.create(itemDanger);
            DossierProjetExigenceEtat itemWarning = new DossierProjetExigenceEtat();
            itemWarning.setId(3L);
            itemWarning.setStyle("warning");
            itemWarning.setLibelle("Pending");
            itemWarning.setCode("Pending");
            dossierProjetExigenceEtatService.create(itemWarning);

    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("FamilleExigence"));
        modelPermissions.add(new ModelPermission("DossierProjetExigence"));
        modelPermissions.add(new ModelPermission("DossierProjet"));
        modelPermissions.add(new ModelPermission("DossierProjetExigenceApplique"));
        modelPermissions.add(new ModelPermission("DossierProjetExigenceEtat"));
        modelPermissions.add(new ModelPermission("Exigence"));
        modelPermissions.add(new ModelPermission("DossierProjetDocument"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


    @Autowired
    FamilleExigenceAdminService familleExigenceService;
    @Autowired
    DossierProjetExigenceEtatAdminService dossierProjetExigenceEtatService;
}


