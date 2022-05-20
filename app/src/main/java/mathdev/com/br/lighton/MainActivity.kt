package mathdev.com.br.lighton


import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    private lateinit var switch: SwitchMaterial
    private lateinit var cameraManager: CameraManager
    private lateinit var camera: String

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialConfiguration()

        flashValidation()

        switchListener()  @RequiresApi(Build.VERSION_CODES.M)
        override fun onResume() {
            super.onResume()
            flashValidation()
        }


    }


    private fun initialConfiguration() {
        switch = findViewById(R.id.switchFlashLight)
        cameraManager = this.getSystemService(CAMERA_SERVICE) as CameraManager
        camera = cameraManager.cameraIdList[0]
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashValidation() {
        when {
            (!hasFlashLight()) -> simpleToast()
            else -> {
                switch.isEnabled = true
                switch.isChecked = true
                activateFlash(true)
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun switchListener() {
        switch.setOnCheckedChangeListener { _, b ->
            when {
                (b) -> activateFlash(true)
                else -> activateFlash(false)
            }

        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun activateFlash(status: Boolean) =
        cameraManager.setTorchMode(camera, status)

    private fun hasFlashLight() =
        packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

    private fun simpleToast() =
        Toast.makeText(this, getString(R.string.infoAboutFlash), Toast.LENGTH_SHORT).show()
}

