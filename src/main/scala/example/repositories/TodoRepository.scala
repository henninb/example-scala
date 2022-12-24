package example.repositories

import example.domain.Todo
import org.springframework.data.jpa.repository.JpaRepository

//@Repository
trait TodoRepository extends JpaRepository [Todo, Long]