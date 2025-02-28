package org.business.tools.calctable.dataprovider.reader.portrait;

import org.business.tools.calctable.dataprovider.parser.common.CalcTableHeaderCellStandardPredicate;
import org.business.tools.calctable.dataprovider.parser.common.CalcTablePrimitiveValueStandardParser;
import org.business.tools.calctable.dataprovider.parser.common.CalcTableStructureNamesStandardResolver;
import org.business.tools.calctable.dataprovider.reader.CalcTableDataReaderConfig;

public class CalcTablePortraitStandardDataReader
		extends
		CalcTablePortraitDataReader
{

	// ... constructors

	public CalcTablePortraitStandardDataReader() {

		super(
			new CalcTableDataReaderConfig(
				CalcTableHeaderCellStandardPredicate.INSTANCE__NON_TRANSPARENT_BACKGROUND,
				CalcTablePrimitiveValueStandardParser.INSTANCE,
				CalcTableStructureNamesStandardResolver.INSTANCE__TO_CAMEL_CASE__IGNORING_PARENTHESES__AND__HASH_SIGN_PREFIXED_COMMENTS
			)
		);
	}

}
