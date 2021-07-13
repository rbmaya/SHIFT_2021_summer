package ru.cft.shift2021summer.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.OptionsGroupBinding
import ru.cft.shift2021summer.databinding.OptionsGroupItemBinding
import ru.cft.shift2021summer.domain.search_options.SearchGroup

class OptionsAdapter : BaseExpandableListAdapter(){

    var listSearchGroups: List<SearchGroup> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getGroupCount(): Int = listSearchGroups.size

    override fun getChildrenCount(groupPosition: Int): Int =
        listSearchGroups[groupPosition].elements.size

    override fun getGroup(groupPosition: Int): Any = listSearchGroups[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        listSearchGroups[groupPosition].elements[childPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun hasStableIds(): Boolean = false

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val groupName = (getGroup(groupPosition) as SearchGroup).name
        return if (convertView == null) {
            val view = layoutInflater.inflate(R.layout.options_group, parent, false)
            val binding = OptionsGroupBinding.bind(view)
            binding.groupTitle.text = groupName
            view
        } else {
            val binding = OptionsGroupBinding.bind(convertView)
            binding.groupTitle.text = groupName
            convertView
        }
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val childGroupName = getChild(groupPosition, childPosition) as String
        return if (convertView == null) {
            val view = layoutInflater.inflate(R.layout.options_group_item, parent, false)
            val binding = OptionsGroupItemBinding.bind(view)
            binding.childGroupTitle.text = childGroupName
            view
        } else {
            val binding = OptionsGroupItemBinding.bind(convertView)
            binding.childGroupTitle.text = childGroupName
            convertView
        }
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}