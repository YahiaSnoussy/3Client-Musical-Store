/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Personne;
import com.mycompany.myapp.services.ServicePersonne;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class AddPersonneForm  extends BaseForm{

    public AddPersonneForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new personne");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom");
        TextField tfPrenom= new TextField("", "Prenom");
        TextField tfRole= new TextField("", "Role");
        TextField tfEmail= new TextField("", "Email");
        TextField tfUsername= new TextField("", "Username");
        TextField tfPassword= new TextField("", "Password");
        TextField tfPhoto= new TextField("", "Photo");
        TextField tfNum_Telephone= new TextField("", "Numero de telephone" );

        Button btnValider = new Button("Add Personne");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfPrenom.getText().length()==0)
                    ||(tfRole.getText().length()==0)||(tfEmail.getText().length()==0)
                        ||(tfUsername.getText().length()==0)||(tfPassword.getText().length()==0)
                        ||(tfPhoto.getText().length()==0)||(tfNum_Telephone.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Personne t = new Personne(tfNom.getText(),tfPrenom.getText(),tfRole.getText(),
                                tfEmail.getText(),tfUsername.getText(),tfPassword.getText(),
                                tfPhoto.getText(),tfNum_Telephone.getText());
                        if( ServicePersonne.getInstance().addPersonne(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        new HomeForm(res).show();
                   
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Erreur", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        
        addAll(tfNom,tfPrenom,tfRole,tfEmail,tfUsername,tfPassword,tfPhoto,tfNum_Telephone,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }

    public AddPersonneForm() {
    }

   
    
    
}
