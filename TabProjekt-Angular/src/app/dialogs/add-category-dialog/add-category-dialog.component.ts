import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { CategoryService } from 'src/app/core/category/category.service';

@Component({
  selector: 'app-add-category-dialog',
  templateUrl: './add-category-dialog.component.html',
  styleUrls: ['./add-category-dialog.component.css']
})
export class AddCategoryDialogComponent implements OnInit {

  public name: string = '';

  constructor(private dialogRef: MatDialogRef<AddCategoryDialogComponent>, private categoryService: CategoryService) {

  }

  ngOnInit(): void {
  }

  add() {
    this.categoryService.add(this.name).subscribe(() => {
      this.categoryService.getCategories().subscribe((data) => {
        this.dialogRef.close({
          data: data
        });
      });
    });
  }
}
