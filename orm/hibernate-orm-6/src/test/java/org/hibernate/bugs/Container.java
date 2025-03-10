package org.hibernate.bugs;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Container {
  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
  private List<Parent> parents;

  public Container(final List<Parent> parents) {
    this.parents = parents;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public List<Parent> getParents() {
    return parents;
  }
}
