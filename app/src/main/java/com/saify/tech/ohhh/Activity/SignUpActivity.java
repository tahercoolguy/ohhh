package com.saify.tech.ohhh.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.SignUpDM;
import com.saify.tech.ohhh.DataModel.VideoDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener {
    AppController appController;
    private static final int IMAGE_PICKER_SELECT = 1;
    private static final int IMAGE_PICKER_SELECT1 = 2;
    private static final int FILE_PICKER_SELECT = 3;
    private static final int IMAGE_VIDEO_ACTIVITY_PICKER = 4;

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;

    @NotEmpty
    @BindView(R.id.firstNameET)
    EditText firstNameET;

    @NotEmpty
    @BindView(R.id.mobileET)
    EditText mobileET;


    @NotEmpty
    @Password
    @BindView(R.id.passwordET)
    EditText passwordET;

    @NotEmpty
    @ConfirmPassword
    @BindView(R.id.confirmPasswordET)
    EditText confirmPasswordET;

    @NotEmpty
    @Email
    @BindView(R.id.emailET)
    EditText emailET;


    @NotEmpty
    @Email
    @BindView(R.id.pinCodeTxt)
    EditText pinCodeTxt;


    @NotEmpty
    @BindView(R.id.Login_Txt)
    TextView Login_Txt;

    @NotEmpty
    @BindView(R.id.sign_up_Txtt)
    TextView signBtn;


    @OnClick(R.id.sign_up_Txtt)
    public void signupBtn()
    {

        if(connectionDetector.isConnectingToInternet())
        {
            boolean correct =true;
            if(firstNameET.getText().toString().equalsIgnoreCase(""))
            {
                correct=false;
                Helper.showToast(SignUpActivity.this,"kindly enter your name");
            }
            else if(emailET.getText().toString().equalsIgnoreCase(""))
            {
                correct=false;
                Helper.showToast(SignUpActivity.this,"kindly enter your email");
            }
            else if (passwordET.getText().toString().equalsIgnoreCase("")){
                correct=false;
                Helper.showToast(SignUpActivity.this,"kindly enter your password");
            }

            else if (confirmPasswordET.getText().toString().equalsIgnoreCase("")){
                correct=false;
                Helper.showToast(SignUpActivity.this,"kindly enter your confirm password");
            }
            else if (pinCodeTxt.getText().toString().equalsIgnoreCase("")) {
                correct=false;
                Helper.showToast(SignUpActivity.this,"kindly enter your country Code");
            }
            else if(mobileET.getText().toString().equalsIgnoreCase("")){
                correct=false;
                Helper.showToast(SignUpActivity.this,"kindly enter your mobile number");
            }
//
//            else if(!checkboxCB.isChecked())
//            {
//                correct=false;
//                Helper.showToast(SignUpActivity.this,"kindly read terms and conditions");
//            }


            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            if(correct) {
                progress = dialogUtil.showProgressDialog(SignUpActivity.this,getString(R.string.please_wait));

                appController.paServices.SignUp(firstNameET.getText().toString(),emailET.getText().toString(), passwordET.getText().toString(),confirmPasswordET.getText().toString(), pinCodeTxt.getText().toString(),mobileET.getText().toString(), new Callback<SignUpDM>(){
                    @Override

                    public void success ( SignUpDM signUpDM, Response response ) {
                        progress.dismiss();
                        if (signUpDM.getStatus().equalsIgnoreCase("1") || signUpDM.getStatus().equalsIgnoreCase("5") || signUpDM.getStatus().equalsIgnoreCase("4") || signUpDM.getStatus().equalsIgnoreCase("3") || signUpDM.getStatus().equalsIgnoreCase("2")) {


                           user.setId(Integer.valueOf(signUpDM.getOutput().getData().get(0).getId()));


                            startActivity(new Intent(SignUpActivity.this, VerifyActivity.class));

                        } else
                            Helper.showToast(SignUpActivity.this, signUpDM.getMessage());
                    }

                    @Override
                    public void failure ( RetrofitError retrofitError ) {
                        progress.dismiss();
                        Log.e("error", retrofitError.toString());

                    }
                });
            }
        }else
            Helper.showToast(SignUpActivity.this,getString(R.string.no_internet_connection));



//        startActivity(new Intent(SignUpActivity.this, VerifyActivity.class));
    }


    @OnClick(R.id.Login_Txt)
    public void signup() {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(SignUpActivity.this);

        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    @Override
    public void onValidationSucceeded() {

    }

    boolean o = true;
    Validator validator;


    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            o = false;
            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Helper.showToast(SignUpActivity.this, message);
            }
        }
    }

    public void isValid() {
        boolean done = true;
        o = true;
        validator.validate();
        //o=done;
        if (!done)
            o = done;

    }


    public void pickImg() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    public void showImagePickerOptions() {

        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();

            }
        });

    }

    int REQUEST_IMAGE = 3;

    private void launchCameraIntent() {
        Intent intent = new Intent(SignUpActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    Uri Video;

    private void launchGalleryIntent() {
        Intent intent = new Intent(SignUpActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    Bitmap mainFile;
    File videoFile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {

//                    videoFile =  new File(uri.getPath());

                    UploadVideo();
                    // You can update this bitmap to your server
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//
//                    mainFile = bitmap;
//                    profilePicture.setImageBitmap(mainFile);
//                    Picasso.with(this).load(uri).transform(new CropCircleTransformation()).into(profilePicture);
                    // loading profile image from local cache
                    //loadProfile(uri.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

//        if (requestCode == VIDEO_CAPTURE) {
//            if (resultCode == RESULT_OK) {
////                Toast.makeText(this, "Video has been saved to:\n" +
////                        data.getData(), Toast.LENGTH_LONG).show();
//
//             Uri uri=   Uri.fromFile(new File(data.getStringExtra("uri")));
//                Toast.makeText(this, "Video has been saved to:\n" +
//                        uri.getPath(), Toast.LENGTH_LONG).show();
//            } else if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(this, "Video recording cancelled.",
//                        Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "Failed to record video",
//                        Toast.LENGTH_LONG).show();
//            }
//        }


        if (requestCode == IMAGE_VIDEO_ACTIVITY_PICKER) {
            if (data != null) {
                if (data.getStringExtra("mode").equalsIgnoreCase("photo")) {
                    Uri.fromFile(new File(data.getStringExtra("uri")));

                } else {
                    Video = Uri.fromFile(new File(data.getStringExtra("uri")));
                    UploadVideo();
                }

            }
        }
    }

    private static final int VIDEO_CAPTURE = 101;
    private Uri fileUri;

    public void startRecording() {
        Intent intent = new Intent(SignUpActivity.this, CameraHandling.class);
        intent.putExtra("mode", "video");
        startActivityForResult(intent, IMAGE_VIDEO_ACTIVITY_PICKER);
    }


    String user_id;

    public void UploadVideo() {
        if (connectionDetector.isConnectingToInternet()) {
            progress = new DialogUtil().showProgressDialog(this, getString(R.string.please_wait));
            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("user_id", new TypedString(user_id));
            File imageFile = new File(getRealPathFromUri(SignUpActivity.this, Video));

//            multipartTypedOutput.addPart("video_file", new TypedFile("video/mp4", imageFile));
            multipartTypedOutput.addPart("video_file", new TypedFile("video/mp4", imageFile));
            appController.paServices.RecordedVideo(multipartTypedOutput, new Callback<VideoDM>() {
                @Override
                public void success(VideoDM videoDM, Response response) {
                    progress.dismiss();
                    Helper.showToast(SignUpActivity.this, "Video Uploaded Successfully");
                    finish();
                }

                @Override
                public void failure(RetrofitError error) {

                    progress.dismiss();
                    Log.e("String", error.toString());
                    Helper.showToast(SignUpActivity.this, "User Created Successfully");
                    finish();

                }
            });
        } else
            Helper.showToast(SignUpActivity.this, getString(R.string.no_internet_connection));
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("Grant Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return contentUri.getPath();
        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
        }
    }
}
