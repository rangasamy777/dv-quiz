package com.dvorakdev.dvquiz;

import com.dvorakdev.dvquiz.context.dvQuizContext;
import com.dvorakdev.dvquiz.model.Category;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class CategoryFormActivity extends Activity {
	
	private Category category;
	
	private EditText categoryNameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_form);
		// Show the Up button in the action bar.
		setupActionBar();		
		
		this.categoryNameEditText = (EditText) this.findViewById(R.id.categoryNameEditText);
		
		this.category = (Category) dvQuizContext.getInstance().getValue("selectedCategory", new Category());
		
		this.categoryNameEditText.setText(this.category.getName());
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category_form, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void processForm(View view)
	{
		if (this.isValid())
		{			
			this.category.setName(this.categoryNameEditText.getText().toString());
			
			this.category.save();
			
			this.setResult(RESULT_OK);
			
			this.finish();
		}
		else
		{
			Toast.makeText(this, String.format(this.getString(R.string.error_object_already_exists), "Category"), Toast.LENGTH_SHORT).show();
		}
	}
	
	public boolean isValid()
	{		
		if (Category.oneByName(this.categoryNameEditText.getText().toString()) != null)
		{
			return false;
		}
		
		return true;				
	}

}
