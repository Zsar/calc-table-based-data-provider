package org.business.tools.calctable.dataprovider.parser.portrait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.business.tools.calctable.dataprovider.common.error.CalcTableException;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;

public class CalcTablePortraitStructureParser {

	// ... business methods

	public List<CalcTableStructureNode> parseStructureArea(
			final Sheet sheet,
			final CalcTableCellsDimension structureAreaDimension
	)
	{

		final List<CalcTableStructureNode> firstLevelStructureNodes = parseStructureArea(
			sheet,
			structureAreaDimension,
			structureAreaDimension.getRow(),
			structureAreaDimension.getColumn()
		);
		return firstLevelStructureNodes;
	}

	private static List<CalcTableStructureNode> parseStructureArea(
			final Sheet sheet,
			final CalcTableCellsDimension structureAreaDimension,
			final int currentRow,
			final int currentColumn
	)
	{

		final Cell currentStructureCell;
		try {
			currentStructureCell = CalcTablePoiNavigationUtils.getCell(
				sheet,
				currentRow,
				currentColumn
			);
		} catch (final CalcTableException ex) {
			return Arrays.asList();
		}

		final String currentStructureCellText = currentStructureCell.getStringCellValue();
		final boolean currentStructureCellEmpty = StringUtils.isBlank(
			currentStructureCellText
		);

		final boolean stopTraversingStructureCells = !structureAreaDimension.contains(
			currentStructureCell.getRowIndex(),
			currentStructureCell.getColumnIndex()
		) || currentStructureCellEmpty;
		if (stopTraversingStructureCells) {

			return Arrays.asList();
		} else {

			final List<CalcTableStructureNode> resultStructureNodes = new ArrayList<>();

			final CalcTableCellsDimension currentStructureCellInnerDimension = CalcTablePoiNavigationUtils.getCellDimension(
				sheet,
				currentStructureCell
			);

			final int childStructureCellsStartRowNum = currentStructureCellInnerDimension.getRow()
					+ currentStructureCellInnerDimension.getRowSpan();
			final int childStructureCellsStartColumnNum = currentStructureCellInnerDimension.getColumn() + 1;

			final List<CalcTableStructureNode> childStructureNodes = parseStructureArea(
				sheet,
				structureAreaDimension,
				childStructureCellsStartRowNum,
				childStructureCellsStartColumnNum
			);

			resultStructureNodes.add(
				CalcTableStructureNode.of(
					currentStructureCellText,
					currentStructureCellInnerDimension,
					childStructureNodes
				)
			);

			final int nextSiblingCellColumnNum = currentStructureCellInnerDimension.getColumn();
			final int nextSiblingCellRowNum;
			if (childStructureNodes.isEmpty()) {

				nextSiblingCellRowNum = currentStructureCellInnerDimension.getRow()
						+ currentStructureCellInnerDimension.getRowSpan();
			} else {
				final CalcTableStructureNode lastChildStructureNode = childStructureNodes.get(
					childStructureNodes.size() - 1
				);
				final CalcTableCellsDimension lastChildCellInnerDimension = lastChildStructureNode.getInnerDimension();
				nextSiblingCellRowNum = lastChildCellInnerDimension.getRow() + lastChildCellInnerDimension.getRowSpan();
			}

			final List<CalcTableStructureNode> siblingStructureNodes = parseStructureArea(
				sheet,
				structureAreaDimension,
				nextSiblingCellRowNum,
				nextSiblingCellColumnNum
			);

			resultStructureNodes.addAll(
				siblingStructureNodes
			);

			return resultStructureNodes;
		}
	}

}
