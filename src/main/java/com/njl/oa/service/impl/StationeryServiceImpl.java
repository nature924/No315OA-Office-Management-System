package com.njl.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.DepartmentHead;
import com.njl.oa.constant.MessageError;
import com.njl.oa.dao.StationeryDao;
import com.njl.oa.entity.Stationery;
import com.njl.oa.entity.StationeryAdd;
import com.njl.oa.entity.StationeryProposer;
import com.njl.oa.service.StationeryService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Transactional
@Service("/stationeryService")
public class StationeryServiceImpl implements StationeryService {

    @Autowired
    private StationeryDao stationeryDao;

    @Override
    public JSONObject getStationeryAll(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int page;
            int limit;
            String pageStr = request.getParameter("page");
            String limitStr = request.getParameter("limit");
            if (pageStr == null || limitStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            page = Integer.parseInt(pageStr);
            limit = Integer.parseInt(limitStr);
            List<Stationery> stationeries = stationeryDao.selectStationeryAll((page - 1) * limit, limit);
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", stationeryDao.selectStationeryAllCount());
            json.put("data", stationeries);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject getHistoryOfOfficeSupplies(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int page;
            int limit;
            String pageStr = request.getParameter("page");
            String limitStr = request.getParameter("limit");
            String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //得到自身工号
            if (pageStr == null || limitStr == null || employeeNumber == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            page = Integer.parseInt(pageStr);
            limit = Integer.parseInt(limitStr);

            List<StationeryProposer> stationeryProposers = stationeryDao.selectStationeryByEmployeeNumber(employeeNumber, (page - 1) * limit, limit);
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", stationeryProposers.size());
            json.put("data", stationeryProposers);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject applyForOfficeSupplies(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String stationeryProposerStr = request.getParameter("stationeryProposer");
            String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //得到自身工号
            if (stationeryProposerStr == null || employeeNumber == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            /**
             * 获得StationeryProposer实体类
             */
            StationeryProposer stationeryProposer = JSONObject.parseObject(stationeryProposerStr, StationeryProposer.class);
            /**
             * 设置审批人ID
             */
            stationeryProposer.setApproveEmployeeId(stationeryDao.selectEmployeeIdByDepartmentHeadId(DepartmentHead.DepartmentAdministrative));
            /**
             * 保存到数据库
             */
            if (stationeryDao.applicationForOfficeSupplies(stationeryProposer, employeeNumber) > 0) {
                //如果大于0|成功
                json.put("type", "success");
                json.put("msg", "申请成功，请等待审批！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "申请失败！");
        return json;
    }

    @Override
    public JSONObject addStationery(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String stationeryStr = request.getParameter("stationery");
            if (stationeryStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            Stationery stationery = JSONObject.parseObject(stationeryStr, Stationery.class);
            if (stationeryDao.addStationery(stationery) > 0) {
                json.put("type", "success");
                json.put("msg", "添加成功");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "添加失败！");
        return json;
    }

    @Override
    public JSONObject updateStationery(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String stationeryStr = request.getParameter("stationery");
            if (stationeryStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            Stationery stationery = JSONObject.parseObject(stationeryStr, Stationery.class);
            if (stationeryDao.updateStationery(stationery) > 0) {
                json.put("type", "success");
                json.put("msg", "修改成功");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "修改失败！");
        return json;
    }

    @Override
    public JSONObject deleteStationery(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int stationeryId;
            String stationeryIdStr = request.getParameter("stationeryId");
            if (stationeryIdStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            stationeryId = Integer.parseInt(stationeryIdStr);
            if (stationeryDao.deleteStationery(stationeryId) > 0) {
                json.put("type", "success");
                json.put("msg", "删除成功");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "删除失败！");
        return json;
    }

    @Override
    public JSONObject getStationeryAddHistory(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int page;
            int limit;
            String pageStr = request.getParameter("page");
            String limitStr = request.getParameter("limit");
            if (pageStr == null || limitStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            page = Integer.parseInt(pageStr);
            limit = Integer.parseInt(limitStr);
            List<StationeryAdd> stationeryAdds = stationeryDao.selectStationeryAddAll((page - 1) * limit, limit);
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", stationeryDao.selectStationeryAddAllCount());
            json.put("data", stationeryAdds);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "添加失败！");
        return json;
    }

    @Override
    public JSONObject addStationeryAdd(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String stationeryAddStr = request.getParameter("stationeryAdd");
            if (stationeryAddStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            StationeryAdd stationeryAdd = JSONObject.parseObject(stationeryAddStr, StationeryAdd.class);
            if (stationeryDao.officeSuppliesWarehouseAdded(stationeryAdd) > 0) {
                if (stationeryDao.updateStationeryCount(stationeryAdd.getStationeryId(), stationeryAdd.getStationeryCount()) > 0) {
                    json.put("type", "success");
                    json.put("msg", "添加成功");
                    return json;
                }
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  //回滚
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "添加失败！");
        return json;
    }

    @Override
    public JSONObject getStationeryApprovalInformation(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int page;
            int limit;
            int approval;
            String pageStr = request.getParameter("page");
            String limitStr = request.getParameter("limit");
            if (pageStr == null || limitStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            page = Integer.parseInt(pageStr);
            limit = Integer.parseInt(limitStr);
            List<StationeryProposer> stationeryProposers = stationeryDao.selectStationeryApprovalInformation((page - 1) * limit, limit);
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", stationeryDao.selectStationeryApprovalInformationCount());
            json.put("data", stationeryProposers);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject stationeryApproverMark(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int count;
            String mark = request.getParameter("mark");
            if (mark == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            StationeryProposer stationeryProposer = JSONObject.parseObject(mark, StationeryProposer.class);
            if (stationeryDao.updateStationeryApprovalApprove(stationeryProposer.getStationeryEmployeeRelId(), stationeryProposer.getApprove(), stationeryProposer.getReceipt()) > 0) {
                if (stationeryProposer.getApprove() == 1) {
                    count = -stationeryProposer.getStationeryCount();
                    System.out.println(count);
                    Integer integer = stationeryDao.updateStationeryCount(stationeryProposer.getStationeryId(), count);
                    if (integer <= 0) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  //回滚
                        json.put("type", "error");
                        json.put("msg", "审批失败！");
                        return json;
                    }
                }
                json.put("type", "success");
                json.put("msg", "审批成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  //回滚
        json.put("type", "error");
        json.put("msg", "审批失败！");
        return json;
    }

    @Override
    public JSONObject stationeryApproverMarkHistory(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int page;
            int limit;
            String pageStr = request.getParameter("page");
            String limitStr = request.getParameter("limit");
            if (pageStr == null || limitStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            page = Integer.parseInt(pageStr);
            limit = Integer.parseInt(limitStr);

            List<StationeryProposer> stationeryProposers = stationeryDao.selectStationeryApproverMarkHistory( (page - 1) * limit, limit);
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", stationeryDao.selectStationeryApproverMarkHistoryCount());
            json.put("data", stationeryProposers);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }
}
