package com.example.rc3b3week2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rc3b3week2.databinding.ActivityMainBinding
import android.R
import android.graphics.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


private lateinit var binding:ActivityMainBinding

data class youTube (val thumbnailImage: Int, val time: String, val createrImage: Int, val title: String, val subTitle: String, val moreIcon: Int)

class MainActivity : AppCompatActivity() {
    private val dataSet = arrayListOf<youTube>()
    private lateinit var rvAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addData()
        rvAdapter = MyAdapter(dataSet)
        binding.rvTest.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvTest.adapter = rvAdapter
        //binding.rvTest.addItemDecoration(DividerItemDecoration(view.getContext(), 1))
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback (
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
        ){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPos: Int = viewHolder.adapterPosition
                val toPos: Int = target.adapterPosition
                rvAdapter.swapData(fromPos, toPos)
                return true
            }
            //스와이프시 데이터 삭제
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                rvAdapter.removeData(viewHolder.layoutPosition)
            }
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val icon: Bitmap
                // actionState가 SWIPE 동작일 때 배경을 빨간색으로 칠하는 작업을 수행하도록 함
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = (itemView.bottom - itemView.top).toFloat()
                    val width = height / 4
                    val paint = Paint()
                    if (dX < 0) {  // 왼쪽으로 스와이프하는지 확인
                        // 뷰홀더의 백그라운드에 깔아줄 사각형의 크기와 색상을 지정
                        paint.color = Color.parseColor("#ff0000")
                        val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                        c.drawRect(background, paint)

                        // 휴지통 아이콘과 표시될 위치를 지정하고 비트맵을 그려줌
                        // 비트맵 이미지는 Image Asset 기능으로 추가하고 drawable 폴더에 위치하도록 함
                        icon = BitmapFactory.decodeResource(resources, R.drawable.ic_menu_delete)
                        val iconDst = RectF(itemView.left.toFloat() - 3  + width*4,
                            (itemView.top.toFloat() + width*1.5).toFloat(),
                            itemView.right.toFloat() + width - width, (itemView.bottom.toFloat() - width*1.3).toFloat()
                        )
                        c.drawBitmap(icon, null, iconDst, null)
                    }
                }

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.rvTest)
    }


    private fun addData() {
        for (i in 0..3) {
            dataSet.add(youTube(com.example.rc3b3week2.R.drawable.thumbnail_image2_ntime, " 17:24 ",com.example.rc3b3week2.R.drawable.creater_image2,
                "카이막은 사드세요..... 제발","승우아빠ㆍ조회수 7.3만회ㆍ1시간전", com.example.rc3b3week2.R.drawable.ic_more))

            dataSet.add(youTube(com.example.rc3b3week2.R.drawable.thumbnail_image3_ntime, " 19:26 ",com.example.rc3b3week2.R.drawable.creater_image3,
                "[롤토체스 강의] 나오면 무조건 하세요 말도안되는 위력을 보여주는 최강 리롤덱 암살트위치덱! 핵심공략...","구루루ㆍ조회수 20만회ㆍ2시간전", com.example.rc3b3week2.R.drawable.ic_more))

            dataSet.add(youTube(com.example.rc3b3week2.R.drawable.thumbnail_image1_ntime, " 4:05 ",com.example.rc3b3week2.R.drawable.creater_image1,
                "[ENG] SMTM10 [풀버전/8회]♬ 리무진 (Feat.MINO)-비오 @본선","Mnet TVㆍ조회수 972만회ㆍ2주전", com.example.rc3b3week2.R.drawable.ic_more))

            dataSet.add(youTube(com.example.rc3b3week2.R.drawable.thumbnail_image4_ntime, " 4:05 ",com.example.rc3b3week2.R.drawable.creater_image4,
                "10CM의 킬링보이스를 라이브로! - 폰서트, 메트리스, pet, 봄이좋냐??, TV를껏네, Perfect, 입김, 스토커...","딩고뮤직 / dingo musicㆍ조회수 712만회ㆍ1개월전", com.example.rc3b3week2.R.drawable.ic_more))
        }
    }


}