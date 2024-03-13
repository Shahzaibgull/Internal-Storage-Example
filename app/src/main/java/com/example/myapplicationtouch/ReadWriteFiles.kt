package com.example.myapplicationtouch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.view.View
import android.widget.Toast

import com.example.myapplicationtouch.databinding.ActivityReadWriteFilesBinding
import java.io.*

class ReadWriteFiles : AppCompatActivity() {
    private lateinit var binding: ActivityReadWriteFilesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityReadWriteFilesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener(View.OnClickListener {
            val file:String =  binding.editFile.text.toString()
            val data:String = binding.editData.text.toString()
            val fileOutputStream:FileOutputStream
            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            Toast.makeText(applicationContext,"data save",Toast.LENGTH_LONG).show()
            binding.editFile.text.clear()
            binding.editData.text.clear()
        })

        binding.btnView.setOnClickListener(View.OnClickListener {
            val filename = binding.editFile.text.toString()
            if(filename.trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while (run {
                        text = bufferedReader.readLine()
                        text } != null)
                {
                    stringBuilder.append(text)
                }
                //Displaying data on EditText
                binding.editData.setText(stringBuilder.toString()).toString()
            }else{
                Toast.makeText(applicationContext,"file name cannot be blank",Toast.LENGTH_LONG).show()
            }
        })
    }
}