package com.dvorakdev.dvquiz.model;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Column.ForeignKeyAction;
import com.activeandroid.query.Select;

public class Quiz extends Model {
	
	@Override
	public String toString()
	{
		return this.getName();
	}
	
	@Column(name = "Category", onDelete = ForeignKeyAction.CASCADE)
	private Category category;

	@Column(name = "Name")
	private String name;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public List<Question> getQuestions()
    {
        return this.getMany(Question.class, "Quiz");
    }
	
	public static Quiz oneByName(String aQuizName)
	{
		return new Select().from(Quiz.class).where("Name = ?", aQuizName).executeSingle();
	}
    
    public static List<Quiz> all()
    {
    	return Model.all(Quiz.class);
    }
    
    public static void truncate()
    {
    	Model.delete(Quiz.class);
    }

}
