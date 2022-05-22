import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CategoryService } from 'src/app/core/category/category.service';

@Component({
  selector: 'app-edit-category-dialog',
  templateUrl: './edit-category-dialog.component.html',
  styleUrls: ['./edit-category-dialog.component.css']
})
export class EditCategoryDialogComponent implements OnInit {

  public nameEmpty = false;

  editForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required])
  });


  constructor(
    private dialogRef: MatDialogRef<EditCategoryDialogComponent>,
    private categoryService: CategoryService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { 
    if(this.data) {
      this.editForm.controls['name'].setValue(this.data.category.categoryName);
    }
  }

  ngOnInit(): void {
  }

  edit() {
    if(this.editForm.invalid) {
      this.nameEmpty = true;
      return;
    }
    this.nameEmpty = false;
    this.data.category.categoryName = this.editForm.controls['name'].value;
    this.categoryService.edit(this.data.category).subscribe((editedCategory) => {
      const index = this.data.categoryList.indexOf(this.data.category);
      this.data.categoryList[index] = editedCategory;
      this.dialogRef.close({
        categoryList: this.data.categoryList
      });
    });
  }

}
