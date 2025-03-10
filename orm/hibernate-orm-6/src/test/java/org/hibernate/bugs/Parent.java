package org.hibernate.bugs;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Parent {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "container_id")
  private Container container;

  @ElementCollection(targetClass = String.class)
  @CollectionTable(name = "parent_value",
      joinColumns = {@JoinColumn(name = "parent_id", referencedColumnName = "id")})
  private List<String> parentList;

  public Parent(final List<String> parentList) {
    this.parentList = parentList;
  }

  public Long getId() {
    return id;
  }

  public void setContainer(final Container container) {
    this.container = container;
  }
}
