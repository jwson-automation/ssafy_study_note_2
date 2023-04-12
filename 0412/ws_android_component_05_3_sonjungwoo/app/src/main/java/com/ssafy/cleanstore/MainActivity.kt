package com.ssafy.cleanstore

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ssafy.cleanstore.databinding.ActivityMainBinding
import com.ssafy.cleanstore.stuff.StuffActivity


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val PERMISSIONS_REQUEST_WRITE_CONTACTS = 1
    private val PERMISSIONS_REQUEST_READ_CONTACTS = 2
    lateinit var btn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var title = binding.title
        var btn = binding.Contactbtn


        title.setOnClickListener() {
            var intent = Intent(this, StuffActivity::class.java)
            startActivity(intent)
        }
        initSaveContactBtn()
    }

    private fun initSaveContactBtn(){
        btn.setOnClickListener() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_CONTACTS),
                    PERMISSIONS_REQUEST_WRITE_CONTACTS
                )
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // READ_CONTACTS 권한이 없을 경우 권한 요청
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    PERMISSIONS_REQUEST_READ_CONTACTS
                )
            } else {
                saveContact()
            }
        }
    }

    // content Provider를 이용한 연락처 저장
    private fun saveContact() {
        val name = "TMP"
        val phone = "010-1234-5678"

        val contentResolver = this.contentResolver
        val contactCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.NUMBER + " = ?",
            arrayOf(phone),
            null
        )

        //
        if (contactCursor != null && contactCursor.moveToFirst()) {
            // READ 해보고 연락처가 있으면 토스트
            Toast.makeText(this, "이미 연락처가 저장되어 있습니다.", Toast.LENGTH_SHORT).show()
            contactCursor.close()
            return
        }

        // URI 만들기
        val rawContactUri =
            contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, ContentValues())
        val rawContactId = ContentUris.parseId(rawContactUri!!)

        // 이름 추가하기
        val nameValues = ContentValues().apply {
            put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
            put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
            )
            put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
        }
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, nameValues)

        // 전화번호 추가하기
        val phoneValues = ContentValues().apply {
            put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
            put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
            )
            put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
            put(
                ContactsContract.CommonDataKinds.Phone.TYPE,
                ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
            )
        }
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, phoneValues)

        // 연락처 저장 토스트
        Toast.makeText(this, "연락처가 저장되었습니다.", Toast.LENGTH_SHORT).show()
    }



    // 권한 요청 결과
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSIONS_REQUEST_WRITE_CONTACTS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveContact()
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.WRITE_CONTACTS
                    )
                ) {
                    Toast.makeText(this, "연락처를 저장하려면 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_CONTACTS),
                        PERMISSIONS_REQUEST_WRITE_CONTACTS
                    )
                } else {
                    // 권한 요청
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_CONTACTS),
                        PERMISSIONS_REQUEST_WRITE_CONTACTS
                    )
                }
            }
        }
    }
}