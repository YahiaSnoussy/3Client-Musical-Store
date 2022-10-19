/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Personne;
import com.mycompany.myapp.services.ServicePersonne;

import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListPersonnesForm extends Form{
    

    public ListPersonnesForm(Resources res) {
        
      
    
        Form previous = Display.getInstance().getCurrent();        

        
        setTitle("List des personnes ");
        
       
     ArrayList<Personne>list = ServicePersonne.getInstance().affichagePersonnes();

for (Personne per : list) {
addButton(per.getNom(),per.getPrenom()); 
    
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    
}
    }
    

    private void addButton(String nom, String prenom) {
Container cnt = new Container();
 
TextArea ta = new TextArea(nom);


ta.setUIID(" Whatever ");
ta.setEditable(false);
cnt.add(ta);
add(cnt);    }

    public ListPersonnesForm() {
    }
}
