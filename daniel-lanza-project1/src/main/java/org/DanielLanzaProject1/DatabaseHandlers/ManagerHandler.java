package org.DanielLanzaProject1.DatabaseHandlers;

import org.DanielLanzaProject1.DataTypes.Manager;
import org.DanielLanzaProject1.DatabaseSQL.ManagerDatabase;

import java.util.List;


public class ManagerHandler {

    private ManagerDatabase managerDb;

    public ManagerHandler(){
        managerDb = new ManagerDatabase();
    }

    public ManagerHandler(ManagerDatabase managerBb){
        this.managerDb = managerBb;
    }


    public int createUser(Manager manager){
        return managerDb.create(manager);
    }

    public List<Manager> getAllManagers(){
        return managerDb.getAll();
    }

    public Manager getByID(int id){
        return managerDb.getId(id);
    }

    public boolean usernameExists(String username){
        Manager m = managerDb.getByUsername(username);
        return (m.getUsername() != null);
    }

    public Manager getByCredentials(String u,String p){
        return managerDb.getByCredentials(u,p);
    }

    public Manager updateManager(Manager manager){
        return managerDb.update(manager);
    }

    public String deleteManager(Manager manager){
        if(managerDb.delete(manager)){
            return "Manager has been successfully deleted";
        }else{
            return "Manager could not be deleted";
        }
    }

}
