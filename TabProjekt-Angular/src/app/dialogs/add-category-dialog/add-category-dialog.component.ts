import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/core/category/category';
import { CategoryService } from 'src/app/core/category/category.service';

@Component({
  selector: 'app-add-category-dialog',
  templateUrl: './add-category-dialog.component.html',
  styleUrls: ['./add-category-dialog.component.css']
})
export class AddCategoryDialogComponent implements OnInit {

  public nameEmpty = false;

  addForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required])
  });

  constructor(
    private dialogRef: MatDialogRef<AddCategoryDialogComponent>,
    private categoryService: CategoryService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {

  }

  ngOnInit(): void {
  }

  add() {
    if(this.addForm.invalid) {
      this.nameEmpty = true;
      return;
    }
    this.nameEmpty = false;

    const newCategory = new Category(null, true, this.addForm.controls['name'].value);
    this.categoryService.add(newCategory).subscribe((savedCategory) => {
      this.data.categoryList.push(savedCategory);
      this.dialogRef.close({
        categoryList: this.data.categoryList
      });
    });
  }
}
