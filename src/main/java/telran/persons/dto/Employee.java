package telran.persons.dto;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class Employee extends Person
{
	public String company;
	public String title;
	public int salary;
	
	public Employee()
	{
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String name, Address address, LocalDate birthDate, String company, String title, int salary)
	{
		super(id, name, address, birthDate);
		this.company = company;
		this.title = title;
		this.salary = salary;
	}

	public String getCompany() {
		return company;
	}

	public String getTitle() {
		return title;
	}

	public int getSalary() {
		return salary;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + salary;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (company == null)
		{
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (salary != other.salary)
			return false;
		if (title == null)
		{
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Employee [company=" + company + ", title=" + title + ", salary=" + salary + "] " +
				super.toString();
	}
}
