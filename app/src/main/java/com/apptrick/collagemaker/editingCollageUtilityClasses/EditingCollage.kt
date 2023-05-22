package com.apptrick.collagemaker.editingCollageUtilityClasses

import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.dataclasses.EditingOptions

class EditingCollage {
    private var listFrame: ArrayList<Int> = ArrayList()

    fun addingFrame(size: Int): ArrayList<Int>
    {
        when(size) {
            2 -> {
                listFrame.add(R.drawable.collage)
                listFrame.add(R.drawable.collage_2_1)
                listFrame.add(R.drawable.collage_2_3)
                listFrame.add(R.drawable.collage_2_2)
                listFrame.add(R.drawable.collage_2_4)
                listFrame.add(R.drawable.collage_2_5)
                listFrame.add(R.drawable.collage_2_6)
                listFrame.add(R.drawable.collage_2_7)
                listFrame.add(R.drawable.collage_2_8)
                listFrame.add(R.drawable.collage_2_9)

                return listFrame
            }
            3 -> {
                listFrame.add(R.drawable.collage_3_0)
                listFrame.add(R.drawable.collage_3_1)
                listFrame.add(R.drawable.collage_3_2)
                listFrame.add(R.drawable.collage_3_3)
                listFrame.add(R.drawable.collage_3_8)
                listFrame.add(R.drawable.collage_3_9)
                listFrame.add(R.drawable.collage_3_10)
                listFrame.add(R.drawable.collage_3_11)
                listFrame.add(R.drawable.collage_3_12)
                listFrame.add(R.drawable.collage_3_13)
                listFrame.add(R.drawable.collage_3_14)
                listFrame.add(R.drawable.collage_3_15)
                listFrame.add(R.drawable.collage_3_16)

                return listFrame
            }
            4 -> {
                listFrame.add(R.drawable.collage_4_0)
                listFrame.add(R.drawable.collage_4_1)
                listFrame.add(R.drawable.collage_4_2)
                listFrame.add(R.drawable.collage_4_3)
                listFrame.add(R.drawable.collage_4_4)
                return listFrame
            }
            5 -> {
                listFrame.add(R.drawable.collage_5_0)
                listFrame.add(R.drawable.collage_5_1)
                listFrame.add(R.drawable.collage_5_2)
                listFrame.add(R.drawable.collage_5_3)
                listFrame.add(R.drawable.collage_5_4)
                listFrame.add(R.drawable.collage_5_5)
                listFrame.add(R.drawable.collage_5_6)
                return listFrame
            }
            6 -> {
                listFrame.add(R.drawable.collage_6_0)
                listFrame.add(R.drawable.collage_6_1)
                listFrame.add(R.drawable.collage_6_2)
                listFrame.add(R.drawable.collage_6_3)
                listFrame.add(R.drawable.collage_6_4)
                listFrame.add(R.drawable.collage_6_5)
                listFrame.add(R.drawable.collage_6_6)
                listFrame.add(R.drawable.collage_6_7)
                return listFrame
            }
            7 -> {
                listFrame.add(R.drawable.collage_7_0)
                listFrame.add(R.drawable.collage_7_1)
                listFrame.add(R.drawable.collage_7_2)
                listFrame.add(R.drawable.collage_7_3)
                listFrame.add(R.drawable.collage_7_4)
                listFrame.add(R.drawable.collage_7_5)
                listFrame.add(R.drawable.collage_7_6)
                listFrame.add(R.drawable.collage_7_7)
                return listFrame
            }
            8 ->{

                return listFrame
            }
            9 ->{

                return listFrame
            }
        }
        return listFrame
    }


    fun addingEditingOptions(): ArrayList<EditingOptions>{
        val listEditOptions = ArrayList<EditingOptions>()
        listEditOptions.add(EditingOptions(R.drawable.ic_baseline_border_vertical_24, "Borders"))
        listEditOptions.add(EditingOptions(R.drawable.ic_baseline_aspect_ratio_24, "Ratio"))
        listEditOptions.add(EditingOptions(R.drawable.ic_baseline_text_fields_24, "Text"))
        listEditOptions.add(EditingOptions(R.drawable.ic_baseline_sticky_note_2_24, "Stickers"))
        listEditOptions.add(EditingOptions(R.drawable.ic_baseline_sticky_note_2_24, "Bg"))
        listEditOptions.add(EditingOptions(R.drawable.ic_baseline_brush_24, "Brush"))
        listEditOptions.add(EditingOptions(R.drawable.ic_baseline_sticky_note_2_24, "Filters"))
        listEditOptions.add(EditingOptions(R.drawable.ic_baseline_sticky_note_2_24, "Frames"))
        listEditOptions.add(EditingOptions(R.drawable.ic_baseline_sticky_note_2_24, "Adjust"))
        return listEditOptions
    }
}


