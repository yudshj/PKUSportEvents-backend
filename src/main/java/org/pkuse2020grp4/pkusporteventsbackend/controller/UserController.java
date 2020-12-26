package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.annotation.CheckedApplyForm;
import org.pkuse2020grp4.pkusporteventsbackend.annotation.CheckedUserId;
import org.pkuse2020grp4.pkusporteventsbackend.dto.ApplyFormDTO;
import org.pkuse2020grp4.pkusporteventsbackend.dto.CheckDTO;
import org.pkuse2020grp4.pkusporteventsbackend.entity.ApplyForm;
import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.exception.UserNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.service.ApplyFormService;
import org.pkuse2020grp4.pkusporteventsbackend.service.UserService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    ApplyFormService applyFormService;

    @Autowired
    UserService userService;

    @PostMapping("/api/userinfo")
    public Result getUserInfo(@CheckedUserId int userId) throws UserNotFoundException {
        return Result.buildSuccessResult("Get user info.", userService.getUserDTOByUserId(userId));
    }

    @PostMapping("/api/perm/apply")
    public Result applyForPermission(@CheckedApplyForm ApplyFormDTO applyFormDTO) {
        applyFormService.saveApplyForm(applyFormDTO);
        return Result.buildSuccessResult("Applied permission.");
    }

    @PostMapping("/api/perm/getall")
    public Result getAllApplies() {
        return Result.buildSuccessResult("Get all applies.", applyFormService.getAll());
    }

    @PostMapping("/api/perm/check")
    public Result checkApply(@RequestBody CheckDTO checkDTO) throws Exception {
        ApplyForm applyForm = applyFormService.getAndRemove(checkDTO.getApplyId());
        if (checkDTO.getAllow() == 1) {
            userService.updatePermission(applyForm.getUserId(), applyForm.getPermission());
        }
        return Result.buildSuccessResult("Checked apply.");
    }
}
