package com.example.wemma.kotlintest

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var a: Int = 0
    var b: String = ""
    var c = listOf<String>("a","b","c")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                methodTest4()
            }
            R.id.nav_gallery -> {
                methodTest5()
            }
            R.id.nav_slideshow -> {
                methodTest6()
            }
            R.id.nav_manage -> {
                methodTest7()
            }
            R.id.nav_share -> {
                methodTest10()
            }
            R.id.nav_send -> {
                methodTest11()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun methodTest(){}
    fun methodTest(param1:String, param2:Int) : Unit{
        Toast.makeText(this, param1+param2, Toast.LENGTH_SHORT).show()
    }
    fun methodTest(param1:Int) : Int{
        var iResult:Int = 1
        iResult = iResult + param1
        return iResult
    }

    /**
     * switch문 대신 when
     */
    fun methodTest(param1: String) : Unit{
        when(param1){
            "a" -> Toast.makeText(this, param1 + "입니다.", Toast.LENGTH_SHORT).show()
            "b" -> Toast.makeText(this, param1 + "입니다.", Toast.LENGTH_SHORT).show()
            else ->{
                Toast.makeText(this, param1 + "는 a또는 b가 아닙니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun methodTest2(param1: String) : Unit{
        when(param1){
            "a", "b" -> Toast.makeText(this, param1 + "입니다.", Toast.LENGTH_SHORT).show()
            "c" -> Toast.makeText(this, param1 + "입니다.", Toast.LENGTH_SHORT).show()
            else ->{
                Toast.makeText(this, param1 + "는 a또는 b또는 c가 아닙니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun methodTest3(param1: String) : Unit{
        val sResult : String =
                if(param1 == "a"){
                    "a"
                }else {
                    "b"
                }
        val sResult2 : String =
                when(param1){
                    "a,b" -> "a,b"
                    "c" -> "c"
                    else -> "other"
                }
    }

    /**
     * thread
     */
    fun methodTest4() : Unit{
        try {
            class mHandler : Handler(){
                override fun handleMessage(msg: Message?) {
                    super.handleMessage(msg)
                    if(msg?.what == 0){
                        txtHello.setText(msg?.obj.toString())
                        Toast.makeText(window.context, msg?.obj.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            Thread{
                for(sValue in c){
                    var handler = mHandler()
                    var message = Message()
                    message.what = 0
                    message.obj = sValue
                    handler?.sendMessage(message)
                }
            }.run()
        } catch (e: Exception) {
            Toast.makeText(this, "토스트여러번 띄우는 쓰레드에서 에러", Toast.LENGTH_SHORT).show()
        }

        Snackbar.make(window.decorView, "스넥바가 떴네요....\n줄도 바뀌나? \n한번더", 3000)
                .setActionTextColor(Color.parseColor("#FF0000"))
                .setAction("버튼", View.OnClickListener {
                    Toast.makeText(this, "스넥바를 여러번 연달아 띄우는 것은 안 되나보다.", Toast.LENGTH_SHORT).show()
                }).show()
    }

    fun methodTest5() : Unit{
        //0부터 10까지 시작과 끝을 포함하는 범위를 정의한다.
        val myRange : IntRange = 0..10
        var stringBuffer : StringBuffer = StringBuffer();
        for(i in myRange){ //10까지 돈다.
            stringBuffer.append(i)
            if(i < myRange.last)
                stringBuffer.append(",")
        }
        Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_LONG).show()
    }

    fun methodTest6() : Unit{
        val iStart : Int = 0
        val iEnd : Int = 15
        var stringBuffer:StringBuffer = StringBuffer();
        for(i in iStart until iEnd){ //15미만까지 돈다.
            stringBuffer.append(i)
            if(i < iEnd)
                stringBuffer.append(",")
        }
        Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_LONG).show()
    }

    /**
     * contain 및 특정범위값 입력
     */
    fun methodTest7():Unit{
        val myRange:IntRange = 0..10
        val foo : Boolean = 5 in myRange
        val foo2 : Boolean = 5 !in myRange

        val myString : String = "abc def ghi jkl"
        val foo3 : Boolean = "defg" in myString //contain 처럼 쓸 수있네?
        Toast.makeText(this, foo3.toString(), Toast.LENGTH_LONG).show()
    }

    /**
     * 오름for문 내림for문
     */
    fun methodTest8() : Unit{
        //54321출력
        for(i in 5 downTo 1){ //1 step씩 작아진다.
            System.out.print(i)
        }
        //531출력
        for(i in 5 downTo 2){ //2 step씩 작아진다.
            System.out.print(i)
        }
    }

    /**
     * try catch의 값 전달
     */
    fun methodTest9() : Unit{
        val valid : Boolean = try {
            true
        }catch (e : Exception){
            false
        }finally {
            true
        }
    }

    /**
     * 엘비스 연산자!! 3항연산자랑 비슷한데 null에 대한 예외처리로 사용한다.
     */
    fun methodTest10() : Unit{
        var tmp : String? = null
        var tmp2 : String = "널 아닙니다."
        Toast.makeText(this, tmp2 ?: "널 입니다.", Toast.LENGTH_SHORT).show();
    }

    /**
     * DTO 생성 (변수정의, getter, setter필요없이 한줄로 가능)
     */
    data class Person(val name:String , val address:String)

    /**
     * DTO에 값 대입 및 비교
     * if문을 이용한 3항 연산
     */
    fun methodTest11() : Unit{
        val jj = Person("aa", "bb")
        val kk = Person("aa", "bb")
        val ll = Person("aa", "cc")
        Toast.makeText(this, if(jj == kk) "오브젝트가 같다" else "오브젝트가 틀리다", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, if(kk.address == ll.address) "변수값이 같다" else "변수값이 틀리다", Toast.LENGTH_SHORT).show()
    }


}
