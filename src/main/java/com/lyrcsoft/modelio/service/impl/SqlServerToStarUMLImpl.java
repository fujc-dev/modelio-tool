package com.lyrcsoft.modelio.service.impl;

import com.lyrcsoft.modelio.boot.AbstractStartUMLService;
import com.lyrcsoft.modelio.boot.model.DataSource;
import com.lyrcsoft.modelio.boot.model.Table;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基于SqlServer数据库生成StarUML
 *
 * @author fujc2dev@126.com
 * @date 2024-05-21 9:06
 */
@Service("sqlserver")
public class SqlServerToStarUMLImpl extends AbstractStartUMLService {

    @Override
    public List<Table> getTableList(DataSource ds) {
        return null;
    }
}
