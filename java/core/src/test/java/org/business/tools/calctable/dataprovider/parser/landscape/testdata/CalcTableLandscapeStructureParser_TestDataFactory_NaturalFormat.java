package org.business.tools.calctable.dataprovider.parser.landscape.testdata;

import static java.util.Arrays.asList;

import org.business.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;

public interface CalcTableLandscapeStructureParser_TestDataFactory_NaturalFormat {

	AbstractCalcTablePojo EXPECTED_STRUCTURE_NODES = //
			CalcTableStructureNode.of(
				"Header Level 1",
				CalcTableCellsDimension.of(
					1,
					1,
					1,
					6
				),
				asList(
					CalcTableStructureNode.of(
						"Header Level 1.1",
						CalcTableCellsDimension.of(
							2,
							1,
							1,
							1
						),
						asList()
					),
					CalcTableStructureNode.of(
						"Header Level 1.2",
						CalcTableCellsDimension.of(
							2,
							2,
							1,
							2
						),
						asList(
							CalcTableStructureNode.of(
								"Header Level 1.2.1",
								CalcTableCellsDimension.of(
									3,
									2,
									1,
									1
								),
								asList()
							),
							CalcTableStructureNode.of(
								"Header Level 1.2.2",
								CalcTableCellsDimension.of(
									3,
									3,
									1,
									1
								),
								asList()
							)
						)
					),
					CalcTableStructureNode.of(
						"Header Level 1.3",
						CalcTableCellsDimension.of(
							2,
							4,
							1,
							3
						),
						asList(
							CalcTableStructureNode.of(
								"Header Level 1.3.1",
								CalcTableCellsDimension.of(
									3,
									4,
									1,
									1
								),
								asList()
							),
							CalcTableStructureNode.of(
								"Header Level 1.3.2",
								CalcTableCellsDimension.of(
									3,
									5,
									1,
									2
								),
								asList(
									CalcTableStructureNode.of(
										"Header Level 1.3.2.1",
										CalcTableCellsDimension.of(
											4,
											5,
											1,
											1
										),
										asList()
									),
									CalcTableStructureNode.of(
										"Header Level 1.3.2.2",
										CalcTableCellsDimension.of(
											4,
											6,
											1,
											1
										),
										asList()
									)
								)
							)
						)
					)
				)
			);

}
