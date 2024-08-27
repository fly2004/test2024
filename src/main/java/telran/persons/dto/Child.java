package telran.persons.dto;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class Child extends Person
{
	public String garten;
	
	public Child()
	{
		// TODO Auto-generated constructor stub
	}

	public Child(int id, String name, Address address, LocalDate birthDate, String garten)
	{
		super(id, name, address, birthDate);
		this.garten = garten;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((garten == null) ? 0 : garten.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Child))
			return false;
		Child other = (Child) obj;
		if (garten == null)
		{
			if (other.garten != null)
				return false;
		} else if (!garten.equals(other.garten))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Child [garten=" + garten + "] " + super.toString();
	}
	
	

}
