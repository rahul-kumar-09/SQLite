package com.example.sqlitedb

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sqlitedb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.addName.setOnClickListener{

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val name = binding.enterName.text.toString()
            val age = binding.enterAge.text.toString()


           if (name.isNotEmpty() && age.isNotEmpty()) {

               // calling method to add
               // name to our database
               db.addName(name, age)

               // Toast to message on the screen
               Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

               // at last, clearing edit texts
               binding.enterName.text.clear()
               binding.enterAge.text.clear()
           } else {
               Toast.makeText(this, "Empty field not allows.", Toast.LENGTH_SHORT).show()
           }


        }

        // below code is to add on  click
        // listener to our print name button
        binding.printName.setOnClickListener{

            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()



                // moving the cursor to first position and
                // appending value in the text view
                cursor!!.moveToFirst()
                binding.Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                binding.Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

                // moving our cursor to next
                // position and appending values
                while (cursor.moveToNext()) {
                    binding.Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                    binding.Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
                }

                // at last we close our cursor
                cursor.close()

        }
    }
}