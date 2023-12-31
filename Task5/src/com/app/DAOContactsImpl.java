package com.app;

import com.db.database;
import com.interfaces.DAOContacts;
import com.models.Contact;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOContactsImpl extends database implements DAOContacts{
    
    String query;
    
    @Override
    public void addContact(Contact contact) throws Exception {
        try{
            this.Connect();
            query = "INSERT INTO contacts(name, email, phone, company) VALUES(?,?,?,?);";
            PreparedStatement st = this.connection.prepareStatement(query);
            st.setString(1, contact.getName());
            st.setString(2, contact.getEmail());
            st.setString(3, contact.getPhone());
            st.setString(4, contact.getCompany());
            st.executeUpdate();
            st.close();
        } catch(Exception e){
            System.out.println(e);
        } finally {
            this.Close();
        }
    }

    @Override
    public ArrayList<Contact> showContacts() throws Exception {
        ArrayList<Contact> contacts = new ArrayList();
        try{
            this.Connect();
            query = "SELECT * FROM contacts;";
            PreparedStatement st = this.connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contact.setCompany(rs.getString("company"));
                contacts.add(contact);
            }
            rs.close();
            st.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            this.Close();
        }
        return contacts;
    }

    @Override
    public ArrayList<Contact> searchContact(String name) throws Exception {
        ArrayList<Contact> contacts = new ArrayList();
        try{
            this.Connect();
            query = "SELECT * FROM contacts WHERE name LIKE '%" + name + "%';";
            PreparedStatement st = this.connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contact.setCompany(rs.getString("company"));
                contacts.add(contact);
            }
            rs.close();
            st.close();
        } catch(Exception e){
            System.out.println(e);
        } finally {
            this.Close();
        }
        return contacts;
    }

    @Override
    public void deleteContact(int id) throws Exception {
        try{
            this.Connect();
            query = "DELETE FROM contacts WHERE id = "+id+";";
            PreparedStatement st = this.connection.prepareStatement(query);
            st.executeUpdate();
            st.close();
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        } finally {
            this.Close();
        }
    }
    
    @Override
    public void editContact(Contact contact) throws Exception {
        try{
            this.Connect();
            query = "UPDATE contacts SET name = ?, email = ?, phone = ?, company = ? WHERE id = ?;";
            PreparedStatement st = this.connection.prepareStatement(query);
            st.setString(1, contact.getName());
            st.setString(2, contact.getEmail());
            st.setString(3, contact.getPhone());
            st.setString(4, contact.getCompany());
            st.setInt(5, contact.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e){
            System.out.println(e);
        } finally {
            this.Close();
        }
    }
    
    
    @Override
    public Contact searchById(int id) throws Exception {
        Contact contact = new Contact();
        try{
            this.Connect();
            query = "SELECT * FROM contacts WHERE id ="+ id +";";
            PreparedStatement st = this.connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contact.setCompany(rs.getString("company"));
            }
        } catch(Exception e){
            System.out.println(e);
        } finally{
            this.Close();
        }
        return contact;
    }
}
