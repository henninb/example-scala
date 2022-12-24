package example.domain

import jakarta.persistence.{Entity, GeneratedValue, Id}

import scala.annotation.meta.field
import scala.beans.BeanProperty

@Entity
case class Todo(@(Id@field)
                      @(GeneratedValue@field)
                      @BeanProperty id: Long,
                      @BeanProperty text: String) {
  def this() = {
    this(0, "")
  }
}