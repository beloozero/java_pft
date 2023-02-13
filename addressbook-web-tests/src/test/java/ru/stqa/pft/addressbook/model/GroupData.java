package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {
  @XStreamOmitField
  @Id
  @Column(name="group_id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name="group_name")
  private String name;
  @Expose
  @Column(name="group_header")
  @Type(type = "text")
  private String header;
  @Expose
  @Column(name="group_footer")
  @Type(type = "text")
  private String footer;

  @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
  private Set<ContactData> contacts = new HashSet<>();

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public int getId() {
    return id;
  }

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", header='" + header + '\'' +
            ", footer='" + footer + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;

    if ( (name != null && !name.equals("") ) && (groupData.name == null || groupData.name.equals(""))) return false;
    if ( (groupData.name != null && !groupData.name.equals("") ) && (name == null || name.equals(""))) return false;
    if (name != null && groupData.name != null && !Objects.equals(name, groupData.name)) return false;

    if ( (header != null && !header.equals("") ) && (groupData.header == null || groupData.header.equals(""))) return false;
    if ( (groupData.header != null && !groupData.header.equals("") ) && (header == null || header.equals(""))) return false;
    if (header != null && groupData.header != null && !Objects.equals(header, groupData.header)) return false;

    if ( (footer != null && !footer.equals("") ) && (groupData.footer == null || groupData.footer.equals(""))) return false;
    if ( (groupData.footer != null && !groupData.footer.equals("") ) && (footer == null || footer.equals(""))) return false;
    if (footer != null && groupData.footer != null && !Objects.equals(footer, groupData.footer)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (header != null ? header.hashCode() : 0);
    result = 31 * result + (footer != null ? footer.hashCode() : 0);
    return result;
  }
}
