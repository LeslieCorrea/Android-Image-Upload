package com.span.example;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
//import com.prgguru.example.R;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	ProgressDialog prgDialog;
	String encodedString;
	RequestParams params = new RequestParams();
	String imgPath, fileName;
	Bitmap bitmap, lesimg;
	private static int RESULT_LOAD_IMG = 1;
	private static int REQUEST_IMAGE_CAPTURE = 1;
	private static String TIME_STAMP="null";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		prgDialog = new ProgressDialog(this);
		// Set Cancelable as False
		prgDialog.setCancelable(false);
	}

	public void loadImagefromGallery(View view) {
		// Create intent to Open Image applications like Gallery, Google Photos
		Intent galleryIntent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		// Start the Intent
		startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
	}
	public void dispatchTakePictureIntent(View view) {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
	    }
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        ImageView imgView = (ImageView) findViewById(R.id.imgView);
	        imgView.setImageBitmap(imageBitmap);
	        lesimg=imageBitmap;
	        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
	        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	        String hjkl=currentDateTimeString.replaceAll(" ", "%20");
	        String hiop=hjkl.replaceAll(":", "%20");
	        TIME_STAMP=hiop;//sdf.toString();
	        fileName=TIME_STAMP+".jpeg";
	        params.put("filename", fileName);
	    }
	}

	// When Image is selected from Gallery
	/*@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		try {
			// When an Image is picked
			if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
					&& null != data) {
				// Get the Image from data

				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				// Get the cursor
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				// Move to first row
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				imgPath = cursor.getString(columnIndex);
				cursor.close();
				ImageView imgView = (ImageView) findViewById(R.id.imgView);
				// Set the Image in ImageView
				imgView.setImageBitmap(BitmapFactory
						.decodeFile(imgPath));
				// Get the Image's file name
				String fileNameSegments[] = imgPath.split("/");
				fileName = fileNameSegments[fileNameSegments.length - 1];
				// Put file name in Async Http Post Param which will used in Java web app
				params.put("filename", fileName);

			} else {
				Toast.makeText(this, "You haven't picked Image",
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
					.show();
		}

	}*/
	
	// When Upload button is clicked
	public void uploadImage(View v) {
		// When Image is selected from Gallery
		/*if (imgPath != null && !imgPath.isEmpty()) {
			prgDialog.setMessage("Converting Image to Binary Data");
			prgDialog.show();*///---------Edited
			// Convert image to String using Base64
			encodeImagetoString();
		// When Image is not selected from Gallery
		/*} else {//---------------Edited
			Toast.makeText(
					getApplicationContext(),
					"You must select image from gallery before you try to upload",
					Toast.LENGTH_LONG).show();
		}*/
	}

	// AsyncTask - To convert Image to String
	public void encodeImagetoString() {
		new AsyncTask<Void, Void, String>() {

			protected void onPreExecute() {

			};

			@Override
			protected String doInBackground(Void... params) {
				BitmapFactory.Options options = null;
				options = new BitmapFactory.Options();
				options.inSampleSize = 3;
				/*bitmap = BitmapFactory.decodeFile(imgPath,
						options);*///-----------Edited
				bitmap=lesimg;
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				// Must compress the Image to reduce image size to make upload easy
				bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream); 
				byte[] byte_arr = stream.toByteArray();
				// Encode Image to String
				encodedString = Base64.encodeToString(byte_arr, 0);
				return "";
			}

			@Override
			protected void onPostExecute(String msg) {
				prgDialog.setMessage("Calling Upload");
				prgDialog.show();
				// Put converted Image string into Async Http Post param
				params.put("image", encodedString);
				// Trigger Image upload
				triggerImageUpload();
			}
		}.execute(null, null, null);
	}
	
	public void triggerImageUpload() {
		makeHTTPCall();
	}

	// http://192.168.2.4:9000/imgupload/upload_image.php
	// http://192.168.2.4:9999/ImageUploadWebApp/uploadimg.jsp
	// Make Http call to upload Image to Java server
	public void makeHTTPCall() {
		prgDialog.setMessage("Invoking JSP");	
		prgDialog.show();
		AsyncHttpClient client = new AsyncHttpClient();
		// Don't forget to change the IP address to your LAN address. Port no as well.
		client.post("http://10.10.221.115:8080/ImageUploadWebApp/uploadimg.jsp",
				params, new AsyncHttpResponseHandler() {
					// When the response returned by REST has Http
					// response code '200'
					@Override
					public void onSuccess(String response) {
						// Hide Progress Dialog
						prgDialog.hide();
						Toast.makeText(getApplicationContext(), response,
								Toast.LENGTH_LONG).show();
					}

					// When the response returned by REST has Http
					// response code other than '200' such as '404',
					// '500' or '403' etc
					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {
						// Hide Progress Dialog
						prgDialog.hide();
						// When Http response code is '404'
						if (statusCode == 404) {
							Toast.makeText(getApplicationContext(),
									"Requested resource not found",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code is '500'
						else if (statusCode == 500) {
							Toast.makeText(getApplicationContext(),
									"Something went wrong at server end",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code other than 404, 500
						else {
							Toast.makeText(
									getApplicationContext(),
									"Error Occured \n Most Common Error: \n1. Device not connected to Internet\n2. Web App is not deployed in App server\n3. App server is not running\n HTTP Status code : "
											+ statusCode, Toast.LENGTH_LONG)
									.show();
						}
					}
				});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// Dismiss the progress bar when application is closed
		if (prgDialog != null) {
			prgDialog.dismiss();
		}
	}
}
