import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/core/category/category';
import { CategoryService } from 'src/app/core/category/category.service';

@Component({
  selector: 'app-edit-category-dialog',
  templateUrl: './edit-category-dialog.component.html',
  styleUrls: ['./edit-category-dialog.component.css']
})
export class EditCategoryDialogComponent implements OnInit {

  public name!: string;
  private editedCategory!: Category;

  constructor(
    private dialogRef: MatDialogRef<EditCategoryDialogComponent>,
    private categoryService: CategoryService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { 
    if(this.data) {
      this.name = this.data.category.categoryName;
      this.editedCategory = this.data.category;
    }
  }

  ngOnInit(): void {
  }

  edit() {
    this.editedCategory.categoryName = this.name;
    this.categoryService.edit(this.editedCategory).subscribe();
    this.dialogRef.close();
  }

}
