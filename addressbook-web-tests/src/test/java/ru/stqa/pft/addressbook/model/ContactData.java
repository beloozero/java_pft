package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstname;
  @Expose
  private String lastname;

  @XStreamOmitField
  @Type(type = "text")
  private String photo;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;
  @Expose
  @Type(type = "text")
  private String phone2;
  @Expose
  @Type(type = "text")
  private String address;
  @XStreamOmitField
  @Transient
  private String allPhones;
  @Expose
  @Type(type = "text")
  private String email;
  @Expose
  @Type(type = "text")
  private String email2;
  @Expose
  @Type(type = "text")
  private String email3;
  @XStreamOmitField
  @Transient
  private String allEmail;
  @XStreamOmitField
  @Transient
  private String group;
  @XStreamOmitField
  @Transient
  private int groupId = 0;

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstname;
  }

  public String getLastName() {
    return lastname;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getPhone2() {
    return phone2;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmail() {
    return allEmail;
  }

  public String getGroup() {
    return group;
  }

  public int getGroupId() {
    return groupId;
  }


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstname = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastname = lastName;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withPhone2(String phone2) {
    this.phone2 = phone2;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmail(String allEmail) {
    this.allEmail = allEmail;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withGroupId(int groupId) {
    this.groupId = groupId;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstname + '\'' +
            ", lastName='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", allEmail='" + allEmail + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;

    if ( (firstname != null && !firstname.equals("") ) && (that.firstname == null || that.firstname.equals(""))) return false;
    if ( (that.firstname != null && !that.firstname.equals("") ) && (firstname == null || firstname.equals(""))) return false;
    if (firstname != null && that.firstname != null && !Objects.equals(firstname, that.firstname)) return false;

    if ( (lastname != null && !lastname.equals("") ) && (that.lastname == null || that.lastname.equals(""))) return false;
    if ( (that.lastname != null && !that.lastname.equals("") ) && (lastname == null || lastname.equals(""))) return false;
    if (lastname != null && that.lastname != null && !Objects.equals(lastname, that.lastname)) return false;

    if ( (address != null && !address.equals("") ) && (that.address == null || that.address.equals(""))) return false;
    if ( (that.address != null && !that.address.equals("") ) && (address == null || address.equals(""))) return false;
    if (address != null && that.address != null && !Objects.equals(address, that.address)) return false;

    if ( (allPhones != null && !allPhones.equals("") ) && (that.allPhones == null || that.allPhones.equals(""))) return false;
    if ( (that.allPhones != null && !that.allPhones.equals("") ) && (allPhones == null || allPhones.equals(""))) return false;
    if (allPhones != null && that.allPhones != null && !Objects.equals(allPhones, that.allPhones)) return false;

    if ( (allEmail != null && !allEmail.equals("") ) && (that.allEmail == null || that.allEmail.equals(""))) return false;
    if ( (that.allEmail != null && !that.allEmail.equals("") ) && (allEmail == null || allEmail.equals(""))) return false;
    if (allEmail != null && that.allEmail != null && !Objects.equals(allEmail, that.allEmail)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
    result = 31 * result + (allEmail != null ? allEmail.hashCode() : 0);
    return result;
  }
}
