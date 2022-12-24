package example.domain

import jakarta.persistence.{Entity, GeneratedValue, Id}

@Entity
class Todo {
  @Id
  @GeneratedValue
  //@BeanProperty
  var id: Long = _

  //@BeanProperty
  //@NotEmpty
  var name: String = _
}