package models

object Student {
  private var students = List[String]()
  
  def addStudent(name: String) = synchronized { students ::= name }
  def clearStudents(): Unit = synchronized { students = Nil }
  def randomStudent(): String = synchronized { students(util.Random.nextInt(students.length)) } 
  def listStudents(): List[String] = students
}
