package com.react.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import com.react.api.dto.SubjectDTO;
import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.service.SubjectService;
import com.react.api.utils.Mappers;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    Mappers mappers;

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long id) {
        SubjectDTO subject = mappers.subjectToSubjectDTO(subjectService.getById(id));
        return new ResponseEntity<SubjectDTO>(subject, HttpStatus.OK);
    }

    /*
     * @GetMapping("/subjects")
     * public ResponseEntity<List<SubjectDTO>> getSubjectListA() {
     * List<SubjectDTO> subjects = mappers
     * .listSubjectToListSubjectDTO(subjectService.findByLastNameWithoutTBA());
     * return new ResponseEntity<List<SubjectDTO>>(subjects, HttpStatus.OK);
     * }
     */

    @PostMapping("/addSubject")
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody SubjectDTO subjectDto) throws BadRequest {
        subjectService.save(mappers.subjectDTOToSubject(subjectDto));
        return new ResponseEntity<>(subjectDto, HttpStatus.CREATED);
    }

    /*
     * @PatchMapping("/editSubject")
     * public ResponseEntity<SubjectDTO> editExistingSubjectById(@PathVariable Long
     * id,
     * 
     * @RequestBody SubjectDTO subjectDto) throws BadRequest, NotFoundException {
     * subjectService.editSubject(subjectDto, id);
     * return new ResponseEntity<>(subjectDto, HttpStatus.ACCEPTED);
     * }
     */

    @DeleteMapping("/deleteSubject/{id}")
    public ResponseEntity deleteSubjectById(@PathVariable Long id) throws NotFoundException {
        subjectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
