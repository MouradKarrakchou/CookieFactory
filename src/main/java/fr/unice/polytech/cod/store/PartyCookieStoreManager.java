package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.decorator_pattern.PartyCookieComponent;

import java.util.ArrayList;
import java.util.List;

public class PartyCookieStoreManager {
    List<PartyChef> listPartyChef;

    public PartyCookieStoreManager() {
        this.listPartyChef = new ArrayList<>();
        this.listPartyChef=new ArrayList<>();
    }

    public boolean hasPartyChef() {
        return !this.listPartyChef.isEmpty();
    }

    public List<PartyChef> getListPartyChef() {
        return listPartyChef;
    }

    public ArrayList<PartyCookieComponent> getPartyCookies() {
        ArrayList<PartyCookieComponent> partyCookieComponents = new ArrayList<>();
        for(PartyChef partyChef : listPartyChef){
            partyCookieComponents.addAll(partyChef.getRecipes());
        }
        return partyCookieComponents;
    }
}
